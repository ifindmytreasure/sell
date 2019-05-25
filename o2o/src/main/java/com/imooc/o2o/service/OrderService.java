package com.imooc.o2o.service;

import com.imooc.o2o.dto.OrderMasterExecution;
import com.imooc.o2o.entity.OrderDetail;
import com.imooc.o2o.entity.OrderMaster;

import java.util.List;

/**
 * Created by Unruly Wind on 2019/5/6/006.
 *
 * @author BlueMelancholy
 * @desc:
 */
public interface OrderService {
	/**
	 * 添加订单和订单详情
	 * @param orderMaster
	 * @param orderDetail
	 * @return
	 */
	OrderMasterExecution addOrder(OrderMaster orderMaster, OrderDetail orderDetail);

	/**
	 * 根据店铺id查询订单
	 * @param orderDetail
	 * @return
	 */
	OrderMasterExecution getOrderDetailList(OrderDetail orderDetail);

	/**
	 * 根据买家id查询所有的订单号，用作查询订单详情
	 * @param buyerId
	 * @return
	 */
	List<Integer> getOrderIdListByBuyerId(Integer buyerId);

	/**
	 * 根据订单号查询订单详情
	 * @param orderId
	 * @return
	 */
	OrderMasterExecution getOrderDetailList(List<Integer> orderId);
}
