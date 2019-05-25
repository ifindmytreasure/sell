package com.imooc.o2o.web.fronted;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.o2o.dto.OrderMasterExecution;
import com.imooc.o2o.entity.*;
import com.imooc.o2o.enums.OrderStatusEnum;
import com.imooc.o2o.service.OrderService;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.util.HttpServletRequestUtil;
import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Unruly Wind on 2019/5/6/006.
 *
 * @author BlueMelancholy
 * @desc:
 */
@RestController
@RequestMapping("/fronted")
@Slf4j
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService productService;
	/**创建订单*/
	@PostMapping("/addorder")
	public Map<String,Object> addOrder(HttpServletRequest request){
		Map<String,Object> modelMap = Collections.synchronizedMap(new HashMap<>(15));
		String orderMasterStr = HttpServletRequestUtil.getString(request,"orderMasterStr");
		String orderDetailStr = HttpServletRequestUtil.getString(request,"orderDetailStr");
		OrderMaster orderMaster = null;
		OrderDetail orderDetail = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			orderMaster = mapper.readValue(orderMasterStr, OrderMaster.class);
			orderDetail = mapper.readValue(orderDetailStr,OrderDetail.class);
		} catch (IOException e) {
			modelMap.put("success",false);
			modelMap.put("errMsg",e.getMessage());
			return modelMap;
		}
		if (orderMaster != null && orderDetail != null){
			Product product = productService.getProductById(orderDetail.getProductId());
			PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
			orderMaster.setBuyerId(user.getUserId().intValue());
			String productPriceStr = orderDetail.getProductPrice();
			String productPrice = productPriceStr.substring(1, productPriceStr.length());
			orderDetail.setProductPrice(productPrice);
			orderDetail.setShop(product.getShop());
			OrderMasterExecution orderMasterExecution = orderService.addOrder(orderMaster, orderDetail);
			if (orderMasterExecution.getState() == OrderStatusEnum.NEW.getState()){
				modelMap.put("success",true);
			}else {
				modelMap.put("success",false);
				modelMap.put("errMsg","请添加订单信息");
			}
			return modelMap;
		}else {
			modelMap.put("success",false);
			modelMap.put("errMsg","请输入订单信息");
			return modelMap;
		}
	}
	/**根据店铺id，详情id查询订单详情*/
	@GetMapping("/getorderdetaillistbycondition")
	public Map<String,Object> getOrderDetailListByCondition(HttpServletRequest request){
		Map<String,Object> modelMap = Collections.synchronizedMap(new HashMap<>(6));
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		long detailId = HttpServletRequestUtil.getLong(request,"detailId");
		OrderDetail orderDetailCondition = new OrderDetail();
		if (shopId > 0){
			Shop shop = new Shop();
			shop.setShopId(shopId);
			orderDetailCondition.setShop(shop);
		}
		if (detailId > 0){
			orderDetailCondition.setDetailId((int)detailId);
		}
		OrderMasterExecution orderMasterExecution = orderService.getOrderDetailList(orderDetailCondition);
		if (orderMasterExecution.getState() == OrderStatusEnum.SUCCESS_GET.getState()){
			modelMap.put("success",true);
			int state = orderMasterExecution.getState();
			String successMsg = OrderStatusEnum.stateOf(state).getStateInfo();
			modelMap.put("successMsg",successMsg);
			modelMap.put("orderDetailList",orderMasterExecution.getOrderDetailList());
		}else {
			modelMap.put("success",false);
			int state = orderMasterExecution.getState();
			String errMsg = OrderStatusEnum.stateOf(state).getStateInfo();
			modelMap.put("errMsg",errMsg);
		}
		return modelMap;
	}
	/** 买家查询订单详情*/
	@GetMapping("/getorderdetaillistbyorderid")
	public Map<String,Object> getOrderDetailListByOrderId(HttpServletRequest request){
		Map<String,Object> modelMap = Collections.synchronizedMap(new HashMap<>(20));
		PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
		if (user == null || user.getUserId() <= 0){
			modelMap.put("success",false);
			modelMap.put("errMsg","未登录");
		}
		Integer userType = user.getUserType();
		log.info("用户类型是" + userType);
		Long buyId = user.getUserId();
//		PersonInfo personInfo = new PersonInfo();
//		personInfo.setUserId(2L);
		List<Integer> orderIdList = orderService.getOrderIdListByBuyerId(buyId.intValue());
		if (orderIdList != null && orderIdList.size() > 0){
			OrderMasterExecution orderMasterExecution = orderService.getOrderDetailList(orderIdList);
			if (orderMasterExecution.getState() == OrderStatusEnum.SUCCESS_GET.getState()){
				modelMap.put("success",true);
				modelMap.put("orderDetailList",orderMasterExecution.getOrderDetailList());
			}else {
				modelMap.put("success",false);
				int state = orderMasterExecution.getState();
				String errMsg = OrderStatusEnum.stateOf(state).getStateInfo();
				modelMap.put("errMsg",errMsg);
			}
		}else {
			modelMap.put("success",false);
			modelMap.put("errMsg","该用户没有任何订单");
		}
		return modelMap;
	}
}
