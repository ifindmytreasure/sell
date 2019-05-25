package com.imooc.o2o.web.fronted;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Unruly Wind on 2019/1/29/029.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Controller
@RequestMapping("/fronted")
public class FrontedController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "fronted/index";
	}

	@RequestMapping(value = "/shoplist", method = RequestMethod.GET)
	public String shopList() {
		return "fronted/shoplist";
	}

	@RequestMapping(value = "/shopdetail", method = RequestMethod.GET)
	public String shopDetail() {
		return "fronted/shopdetail";
	}

	@RequestMapping(value = "/productdetail", method = RequestMethod.GET)
	public String productDetail() {
		return "fronted/productdetail";
	}

	@RequestMapping(value = "/awardlist", method = RequestMethod.GET)
	public String awardList() {
		return "fronted/awardlist";
	}

	@RequestMapping(value = "/pointrecord", method = RequestMethod.GET)
	public String pointrecord() {
		return "fronted/pointrecord";
	}

	/**
	 * 奖品详情页路由
	 *
	 * @return
	 */
	@RequestMapping(value = "/myawarddetail", method = RequestMethod.GET)
	private String showMyAwardDetail() {
		return "fronted/myawarddetail";
	}

	/**
	 * 消费记录列表页路由
	 *
	 * @return
	 */
	@RequestMapping(value = "/myrecord", method = RequestMethod.GET)
	private String showMyRecord() {
		return "fronted/myrecord";
	}

	@RequestMapping(value = "/mypoint", method = RequestMethod.GET)
	private String mypoint() {
		return "fronted/mypoint";
	}

	@RequestMapping(value = "/awarddetail", method = RequestMethod.GET)
	private String awarddetail() {
		return "fronted/awarddetail";
	}

	@RequestMapping(value = "/addorderinfo",method = RequestMethod.GET)
	private String addOrder(){
		return "fronted/addorderinfo";
	}

	@RequestMapping(value = "/orderinfo",method = RequestMethod.GET)
	private String orderInfo(){
		return "fronted/orderinfo";
	}
	@RequestMapping(value = "/orderdetailinfo",method = RequestMethod.GET)
	private String orderDetailInfo(){
		return "fronted/orderdetailinfo";
	}
	@RequestMapping(value = "/pay",method = RequestMethod.GET)
	private String pay(){
		return "fronted/pay";
	}
}
