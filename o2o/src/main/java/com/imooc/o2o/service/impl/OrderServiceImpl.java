package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.OrderDetailDao;
import com.imooc.o2o.dao.OrderMasterDao;
import com.imooc.o2o.dto.OrderMasterExecution;
import com.imooc.o2o.entity.OrderDetail;
import com.imooc.o2o.entity.OrderMaster;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.enums.OrderStatusEnum;
import com.imooc.o2o.exceptions.OrderDetailOperateException;
import com.imooc.o2o.exceptions.OrderMasterOperateException;
import com.imooc.o2o.service.OrderService;
import com.imooc.o2o.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Created by Unruly Wind on 2019/5/6/006.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMasterDao orderMasterDao;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderDetailDao orderDetailDao;
	@Override
	@Transactional
	public OrderMasterExecution addOrder(OrderMaster orderMaster, OrderDetail orderDetail) {
		BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
		Product product = productService.getProductById(orderDetail.getProductId());
		if (orderMaster == null || orderDetail == null){
			return new OrderMasterExecution(OrderStatusEnum.NULL_ORDER);
		}
		try {
			/** 订单的默认信息*/
			orderMaster.setCreateTime(new Date());
			orderMaster.setUpdateTime(new Date());
			String promotionPrice = product.getPromotionPrice();
			BigDecimal productPrice = new BigDecimal(promotionPrice);
			orderAmount = productPrice.multiply(new BigDecimal(orderDetail.getProductQuantity()));
			orderMaster.setOrderAmount(orderAmount);
			/** 订单详情的默认信息*/
			orderDetail.setCreateTime(new Date());
			orderDetail.setUpdateTime(new Date());
			int effectNum = orderMasterDao.insertOrderMaster(orderMaster);
			if (effectNum <= 0){
				throw new OrderMasterOperateException("订单创建失败");
			}
			orderDetail.setOrderMaster(orderMaster);
			int effectedNum = orderDetailDao.insertOrderDetail(orderDetail);
			if (effectedNum <= 0){
				throw new OrderDetailOperateException("订单详情创建失败");
			}
		} catch (Exception e) {
			throw new OrderMasterOperateException("addOrder error" + e.getMessage());
		}
		return new OrderMasterExecution(OrderStatusEnum.NEW,orderMaster,orderDetail);
	}

	@Override
	public OrderMasterExecution getOrderDetailList(OrderDetail orderDetail) {
		OrderMasterExecution orderMasterExecution = new OrderMasterExecution();
		if (orderDetail != null){
			List<OrderDetail> orderDetails = orderDetailDao.queryOrderDetailList(orderDetail);
			if (orderDetails != null && orderDetails.size() > 0){
				orderMasterExecution.setOrderDetailList(orderDetails);
				orderMasterExecution.setState(OrderStatusEnum.SUCCESS_GET.getState());
			}else {
				orderMasterExecution.setState(OrderStatusEnum.NULL_ORDERDETAIL.getState());
			}
		}else {
			orderMasterExecution.setState(OrderStatusEnum.NULL_CONDITION.getState());
		}
		return orderMasterExecution;
	}

	/**
	 * 根据买家id查询所有的订单号，用作查询订单详情
	 *
	 * @param buyerId
	 * @return
	 */
	@Override
	public List<Integer> getOrderIdListByBuyerId(Integer buyerId) {
		return orderMasterDao.queryOrderIdListByBuyerId(buyerId);
	}

	/**
	 * 根据订单号查询订单详情
	 *
	 * @param orderId
	 * @return
	 */
	@Override
	public OrderMasterExecution getOrderDetailList(List<Integer> orderId) {
		OrderMasterExecution orderMasterExecution = new OrderMasterExecution();
		List<OrderDetail> orderDetails = orderDetailDao.queryOrderDetailListByOrderIds(orderId);
		if (orderDetails != null && orderDetails.size() > 0){
			orderMasterExecution.setOrderDetailList(orderDetails);
			orderMasterExecution.setState(OrderStatusEnum.SUCCESS_GET.getState());
		}else {
			orderMasterExecution.setState(OrderStatusEnum.NULL_ORDERDETAIL.getState());
		}
		return orderMasterExecution;
	}
}
