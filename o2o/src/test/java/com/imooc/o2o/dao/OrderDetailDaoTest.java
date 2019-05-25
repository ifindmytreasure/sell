package com.imooc.o2o.dao;

import com.imooc.o2o.entity.OrderDetail;
import com.imooc.o2o.entity.OrderMaster;
import com.imooc.o2o.entity.Shop;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by Unruly Wind on 2019/5/6/006.
 *
 * @author BlueMelancholy
 * @desc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {
	@Autowired
	private OrderDetailDao orderDetailDao;
	@Autowired
	private OrderMasterDao orderMasterDao;
	@Test
	public void testInsertOrderDetail(){
		OrderDetail orderDetail = new OrderDetail();
		OrderMaster orderMaster = new OrderMaster();
		orderMaster.setOrderId(2);
		Shop shop = new Shop();
		shop.setShopId(1L);
		orderDetail.setOrderMaster(orderMaster);
		orderDetail.setShop(shop);
		orderDetail.setProductId(2);
		orderDetail.setProductName("西红柿");
		orderDetail.setProductPrice("23");
		orderDetail.setProductQuantity(10);
		orderDetail.setCreateTime(new Date());
		orderDetail.setUpdateTime(new Date());
		int effectedNum = orderDetailDao.insertOrderDetail(orderDetail);
		System.out.println(effectedNum);
	}
	@Test
	public void testQueryOrderDetailList(){
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setDetailId(4);

		List<OrderDetail> orderDetails = orderDetailDao.queryOrderDetailList(orderDetail);
		System.out.println(orderDetails.size());
		List<String> collect = orderDetails.stream().map(OrderDetail::toString).collect(Collectors.toList());
		System.out.println(collect);
	}
	@Test
	public void testQueryOrderDetailByOrderId(){
		List<Integer> orderIdList = orderMasterDao.queryOrderIdListByBuyerId(2);
		List<OrderDetail> orderDetails = orderDetailDao.queryOrderDetailListByOrderIds(orderIdList);
		System.out.println(orderDetails.size());
		List<String> collect = orderDetails.stream().map(OrderDetail::toString).collect(Collectors.toList());
		System.out.println(collect);
	}
}