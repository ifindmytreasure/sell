package com.imooc.o2o.web.fronted;

import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.service.AreaService;
import com.imooc.o2o.service.ShopCategoryService;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Unruly Wind on 2019/1/31/031.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Controller
@RequestMapping("/fronted")
public class ShopListController {
	@Autowired
	private AreaService areaService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopCategoryService shopCategoryService;

	/**
	 * 返回商品列表页里的ShopCategory列表（一级或者二级），以及区域信息
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/listshopspageinfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listShopsPageInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//从前端获取parentId
		long parentId = HttpServletRequestUtil.getLong(request, "parentId");
		List<ShopCategory> shopCategoryList = null;
		//如果parentId存在，则取出该一级shopCategory下的二级shopCategory列表
		if (parentId != -1) {
			try {
				ShopCategory shopCategoryCondition = new ShopCategory();
				ShopCategory parent = new ShopCategory();
				parent.setShopCategoryId(parentId);
				shopCategoryCondition.setParent(parent);
				shopCategoryList = shopCategoryService.getShopCategoryList(shopCategoryCondition);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
				return modelMap;
			}
		} else {
			try {
				shopCategoryList = shopCategoryService.getShopCategoryList(null);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
				return modelMap;
			}
		}
		modelMap.put("shopCategoryList", shopCategoryList);
		List<Area> areaList = null;
		try {
			//获取区域信息
			areaList = areaService.getAreaList();
			modelMap.put("areaList", areaList);
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		return modelMap;
	}

	/**
	 * 根据指定条件查询店铺列表
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "listshops", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listShops(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//获取页码
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		//获取一页显示的数据
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		if ((pageIndex > -1) && (pageSize > -1)) {
			//获取一级类别Id
			long parentId = HttpServletRequestUtil.getLong(request, "parentId");
			//获取二级类别Id
			long shopCategoryId = HttpServletRequestUtil.getLong(request, "shopCategoryId");
			//获取区域Id
			int areaId = HttpServletRequestUtil.getInt(request, "areaId");
			//获取模糊查询的名字
			String shopName = HttpServletRequestUtil.getString(request, "shopName");
			//组合条件
			Shop shopCondition = compactShopCondition(parentId, shopCategoryId, areaId, shopName);
			ShopExecution shopExecution = shopService.getShopList(shopCondition, pageIndex, pageSize);
			modelMap.put("shopList", shopExecution.getShopList());
			modelMap.put("count", shopExecution.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex");
		}
		return modelMap;
	}

	private Shop compactShopCondition(long parentId, long shopCategoryId, int areaId, String shopName) {
		Shop shopCondition = new Shop();
		if (parentId != -1) {
			// 查询某个一级ShopCategory下面的所有二级ShopCategory里面的店铺列表
			ShopCategory childCategory = new ShopCategory();
			ShopCategory parentCategory = new ShopCategory();
			parentCategory.setShopCategoryId(parentId);
			childCategory.setParent(parentCategory);
			shopCondition.setShopCategory(childCategory);
		}
		if (shopCategoryId != -1L) {
			// 查询某个二级ShopCategory下面的店铺列表
			ShopCategory shopCategory = new ShopCategory();
			shopCategory.setShopCategoryId(shopCategoryId);
			shopCondition.setShopCategory(shopCategory);
		}
		if (areaId != -1L) {
			// 查询位于某个区域Id下的店铺列表
			Area area = new Area();
			area.setAreaId(areaId);
			shopCondition.setArea(area);
		}
		if (shopName != null) {
			// 查询名字里包含shopName的店铺列表
			shopCondition.setShopName(shopName);
		}
		shopCondition.setEnableStatus(1);
		return shopCondition;
	}
}
