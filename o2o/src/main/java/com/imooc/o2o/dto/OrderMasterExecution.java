package com.imooc.o2o.dto;

import com.imooc.o2o.entity.OrderDetail;
import com.imooc.o2o.entity.OrderMaster;
import com.imooc.o2o.enums.OrderStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Unruly Wind on 2019/5/6/006.
 *
 * @author BlueMelancholy
 * @desc:
 */
@NoArgsConstructor
@Data
public class OrderMasterExecution {
	private int state;
	private String stateInfo;
	private int count;
	//操作的订单（增删改时用到）
	private OrderMaster orderMaster;
	private OrderDetail orderDetail;
	//订单列表（订单查询的时候所用）
	private List<OrderMaster> orderMasterList;
	private List<OrderDetail> orderDetailList;

	/**
	 * 订单操作失败用的构造器
	 * @param orderStatusEnum
	 */
	public OrderMasterExecution(OrderStatusEnum orderStatusEnum) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	/**
	 * 店铺操作成功的时候使用的构造器
	 *
	 * @param orderStatusEnum
	 * @param orderMaster
	 */
	public OrderMasterExecution(OrderStatusEnum orderStatusEnum, OrderMaster orderMaster, OrderDetail orderDetail) {
		this.state = orderStatusEnum.getState();
		this.stateInfo = orderStatusEnum.getStateInfo();
		this.orderMaster = orderMaster;
		this.orderDetail = orderDetail;
	}

	/**
	 * 店铺操作成功的时候使用的构造器
	 *
	 * @param stateEnum
	 * @param orderMasterList
	 */
	public OrderMasterExecution(OrderStatusEnum stateEnum, List<OrderMaster> orderMasterList,List<OrderDetail> orderDetailList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.orderMasterList = orderMasterList;
		this.orderDetailList = orderDetailList;
	}
	/**
	 * 店铺操作成功的时候使用的构造器
	 *
	 * @param stateEnum
	 * @param orderDetailList
	 */
	public OrderMasterExecution(OrderStatusEnum stateEnum, List<OrderDetail> orderDetailList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.orderDetailList = orderDetailList;
	}
}
