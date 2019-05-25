package com.imooc.o2o.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.service.AreaService;
import com.imooc.o2o.service.ShopCategoryService;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.CodeUtil;
import com.imooc.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * Created by Unruly Wind on 2018/12/25/025.
 *
 * @author BlueMelancholy
 * @desc:店铺管理控制器
 */
@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private AreaService areaService;

	@RequestMapping(value = "/getshopbyid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopById(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>(10);
		Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if (shopId > -1) {
			try {
				Shop shop = shopService.getShopById(shopId);
				List<Area> areaList = areaService.getAreaList();
				modelMap.put("success", true);
				modelMap.put("shop", shop);
				modelMap.put("areaList", areaList);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty shopId");
		}
		return modelMap;
	}

	@RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getShopInitInfo() {
		Map<String, Object> modelMap = new HashMap<String, Object>(10);
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		List<Area> areaList = new ArrayList<Area>();
		try {
			shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
			areaList = areaService.getAreaList();
			modelMap.put("success", true);
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("areaList", areaList);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		return modelMap;
	}

	@RequestMapping(value = "/getshopmanagementinfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getManagementInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>(16);
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if (shopId <= 0) {
			Object currentShopObj = request.getSession().getAttribute("currentShop");
			if (currentShopObj == null) {
				modelMap.put("redirect", true);
				modelMap.put("url", "/o2o/shopadmin/shoplist");
			} else {
				Shop currentShop = (Shop) currentShopObj;
				modelMap.put("redirect", false);
				modelMap.put("shopId", currentShop.getShopId());
			}
		} else {
			Shop currentShop = new Shop();
			currentShop.setShopId(shopId);
			request.getSession().setAttribute("currentShop", currentShop);
			modelMap.put("redirect", false);
		}
		return modelMap;
	}

	@RequestMapping(value = "/getshoplist", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getShopList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>(16);
		PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
		try {
			Shop shopCondition = new Shop();
			shopCondition.setOwner(user);
			ShopExecution shopExecution = shopService.getShopList(shopCondition, 0, 50);
			modelMap.put("shopList", shopExecution.getShopList());
			// 列出店铺成功之后，将店铺放入session中作为权限验证依据，即该帐号只能操作它自己的店铺
			request.getSession().setAttribute("shopList", shopExecution.getShopList());
			modelMap.put("success", true);
			modelMap.put("user", user);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	@RequestMapping(value = "/registershop", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> registerShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>(10);
		// 1.接收并转化相应的参数，包括店铺信息以及图片信息
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		//使用jackson
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		/**
		 * 记录验证码的验证信息
		 */
		if (CodeUtil.checkVerifyCode(request) == false) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码错误，请重新输入");
			return modelMap;
		}
		try {
			//将shopStr转化成Shop对象
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (IOException e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		//接受前端上次的图片
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传的图片不能为空");
			return modelMap;
		}
		//2.注册店铺信息
		if (shop != null && shopImg != null) {

			PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
			shop.setOwner(owner);
			ShopExecution shopExecution = null;
			try {
				ImageHolder imageHolder = new ImageHolder(shopImg.getOriginalFilename(), shopImg.getInputStream());
				shopExecution = shopService.addShop(shop, imageHolder);
				if (shopExecution.getState() == ShopStateEnum.CHECK.getState()) {
					modelMap.put("success", true);
					//session中该用户可以操作的店铺列表
					List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
					if (shopList == null || shopList.size() == 0) {
						shopList = new ArrayList<Shop>();
					}
					shopList.add(shopExecution.getShop());
					request.getSession().setAttribute("shopList", shopList);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", "请添加店铺信息");
				}
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺信息");
			return modelMap;
		}
	}

	@RequestMapping(value = "/modifyshop", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> modifyShop(HttpServletRequest request) {
		Map<String, Object> modelMap = Collections.synchronizedMap(new HashMap<String, Object>(10));
		// 1.接收并转化相应的参数，包括店铺信息以及图片信息
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		//使用jackson
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		/**
		 * 记录验证码的验证信息
		 */
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码错误，请重新输入");
			return modelMap;
		}
		try {
			//将shopStr转化成Shop对象
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (IOException e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		//接受前端上次的图片
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		}
		//2.修改店铺信息
		if (shop != null && shop.getShopId() != null) {
			ShopExecution shopExecution = null;
			try {
				if (shopImg == null) {
					shopExecution = shopService.modifyShop(shop, null);
				} else {
					ImageHolder imageHolder = new ImageHolder(shopImg.getOriginalFilename(), shopImg.getInputStream());
					shopExecution = shopService.modifyShop(shop, imageHolder);
				}
				System.out.println();
				if (shopExecution.getState() == ShopStateEnum.CHECK.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", shopExecution.getStateInfo());
				}
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺信息");
			return modelMap;
		}
	}
}
