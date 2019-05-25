package com.imooc.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Unruly Wind on 2018/12/27/027.
 *
 * @author BlueMelancholy
 * @desc: 辅助页面的转发
 */
@Controller
@RequestMapping(value = "/shopadmin", method = {RequestMethod.GET})
public class ShopAdminController {
	@RequestMapping("/shopoperation")
	public String shopOperation() {
		//转发至店铺操作页面
		return "/shop/shopoperation";
	}

	@RequestMapping("/shoplist")
	public String shopList() {
		//转发至店铺列表页面
		return "/shop/shoplist";
	}

	@RequestMapping(value = "/shopmanagement")
	public String shopManagement() {
		// 转发至店铺管理页面
		return "shop/shopmanagement";
	}

	@RequestMapping(value = "/productcategorymanagement")
	public String productCategoryManagement() {
		// 转发至商品类别管理页面
		return "shop/productcategorymanagement";
	}

	@RequestMapping(value = "/productoperation")
	public String productOperation() {
		// 转发至商品类别管理页面
		return "shop/productoperation";
	}

	@RequestMapping(value = "/productmanagement")
	public String productManagement() {
		// 转发至商品管理页面
		return "shop/productmanagement";
	}

	@RequestMapping(value = "/shopauthmanagement")
	public String shopAuthManagement() {
		// 转发至店铺授权页面
		return "shop/shopauthmanagement";
	}

	@RequestMapping(value = "/shopauthedit")
	public String shopAuthEdit() {
		// 转发至授权信息修改页面
		return "shop/shopauthedit";
	}

	@RequestMapping(value = "/operationsuccess", method = RequestMethod.GET)
	private String operationSuccess() {
		// 转发至操作失败的页面
		return "shop/operationsuccess";
	}

	@RequestMapping(value = "/operationfail", method = RequestMethod.GET)
	private String operationFail() {
		// 转发至操作成功的页面
		return "shop/operationfail";
	}

	@RequestMapping(value = "/productbuycheck", method = RequestMethod.GET)
	private String productBuyCheck() {
		// 转发至店铺的消费记录的页面
		return "shop/productbuycheck";
	}

	@RequestMapping(value = "/usershopcheck", method = RequestMethod.GET)
	private String userShopCheck() {
		// 店铺用户积分统计路由
		return "shop/usershopcheck";
	}

	@RequestMapping(value = "/awarddelivercheck", method = RequestMethod.GET)
	private String awardDeliverCheck() {
		// 店铺用户积分统计路由
		return "shop/awarddelivercheck";
	}

	@RequestMapping(value = "/awardmanagement", method = RequestMethod.GET)
	private String awardmanagement() {
		// 店铺用户积分统计路由
		return "shop/awardmanagement";
	}

	@RequestMapping(value = "/awardoperation", method = RequestMethod.GET)
	private String awardoperation() {
		// 店铺用户积分统计路由
		return "shop/awardoperation";
	}
	@RequestMapping(value = "/ordermanagement", method = RequestMethod.GET)
	private String orderManagement() {
		return "shop/ordermanagement";
	}
	@RequestMapping(value = "/orderdetailmanagement", method = RequestMethod.GET)
	private String orderDetailManagement() {
		return "shop/orderdetailmanagement";
	}
}
