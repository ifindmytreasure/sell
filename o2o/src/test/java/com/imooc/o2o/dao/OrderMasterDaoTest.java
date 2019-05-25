package com.imooc.o2o.dao;

import com.imooc.o2o.entity.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Unruly Wind on 2019/5/6/006.
 *
 * @author BlueMelancholy
 * @desc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {
	@Autowired
	private OrderMasterDao orderMasterDao;

	@Test
	public void testInsertOrderMasterDao(){
		OrderMaster orderMaster = new OrderMaster();
		orderMaster.setBuyerName("李四");
		orderMaster.setBuyerAddress("江苏盐城");
		orderMaster.setBuyerPhone("1231451243");
		orderMaster.setBuyerId(4);
		orderMaster.setOrderAmount(new BigDecimal(15));
		orderMaster.setCreateTime(new Date());
		orderMaster.setUpdateTime(new Date());
		int effectNum = orderMasterDao.insertOrderMaster(orderMaster);
		System.out.println(effectNum);
	}
	@Test
	public void queryOrderMaster(){
		System.out.println(orderMasterDao.queryOrderMasterByOrderId(1));
	}
	@Test
	public void queryOrderIdByBuyerId(){
		System.out.println(orderMasterDao.queryOrderIdListByBuyerId(4));
	}
}