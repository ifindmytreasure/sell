package com.imooc.o2o.web.fronted;

import com.imooc.o2o.entity.HeadLine;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.service.HeadLineService;
import com.imooc.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Unruly Wind on 2019/1/29/029.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Controller
@RequestMapping("/fronted")
public class MainPageController {
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private HeadLineService headLineService;

	@RequestMapping(value = "/listmainpageinfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listMainPageInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		try {
			//获取一级店铺类别列表(即parentId为空的shopCategory)
			shopCategoryList = shopCategoryService.getShopCategoryList(null);
			modelMap.put("shopCategoryList", shopCategoryList);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		List<HeadLine> headLineList = new ArrayList<HeadLine>();
		HeadLine headLineCondition = new HeadLine();
		try {
			headLineCondition.setEnableStatus(1);
			headLineList = headLineService.getHeadLineList(headLineCondition);
			modelMap.put("headLineList", headLineList);
			PersonInfo user = new PersonInfo();
			user = (PersonInfo) request.getSession().getAttribute("user");
			modelMap.put("user", user);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		modelMap.put("success", true);
		return modelMap;
	}
}
