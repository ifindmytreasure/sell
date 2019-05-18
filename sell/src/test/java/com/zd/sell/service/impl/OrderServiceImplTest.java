package com.zd.sell.service.impl;

import com.zd.sell.dto.OrderDTO;
import com.zd.sell.entity.OrderDetail;
import com.zd.sell.enums.OrderStatusEnum;
import com.zd.sell.enums.PayStatusEnum;
import com.zd.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Unruly Wind on 2019/4/6/006.
 *
 * @author BlueMelancholy
 * @desc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
	@Autowired
	private OrderService orderService;
	private static final String BUYER_OPENID = "123123";

	private static final String ORDER_ID = "1554563395760275147";
	@Test
	public void create() {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setBuyerName("张三");
		orderDTO.setBuyerAddress("江苏");
		orderDTO.setBuyerPhone("123456789012");
		orderDTO.setBuyerOpenid(BUYER_OPENID);
		//购物车
		List<OrderDetail> orderDetailList = new ArrayList<>();
		OrderDetail o1 = new OrderDetail();
		o1.setProductId("123456");
		o1.setProductQuantity(1);
		OrderDetail o2 = new OrderDetail();
		o2.setProductId("123457");
		o2.setProductQuantity(2);
		orderDetailList.add(o1);
		orderDetailList.add(o2);
		orderDTO.setOrderDetailList(orderDetailList);
		OrderDTO result = orderService.create(orderDTO);
		System.out.println(result);
	}

	@Test
	public void findOne() {
		OrderDTO orderDTO = orderService.findOne(ORDER_ID);
		log.info("【查询单个订单】result={}", orderDTO);
		System.out.println(orderDTO.getOrderId());
	}

	@Test
	public void findList() {
		PageRequest pageRequest = PageRequest.of(0,10);
		Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID,pageRequest);
		System.out.println(orderDTOPage.getContent());
	}

	@Test
	public void cancel() {
		OrderDTO orderDTO = orderService.findOne(ORDER_ID);
		OrderDTO result = orderService.cancel(orderDTO);
		Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
	}

	@Test
	public void finish() {
		OrderDTO orderDTO = orderService.findOne(ORDER_ID);
		OrderDTO result = orderService.finish(orderDTO);
		Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
	}

	@Test
	public void paid() {
		OrderDTO orderDTO = orderService.findOne(ORDER_ID);
		OrderDTO result = orderService.paid(orderDTO);
		Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
	}
}