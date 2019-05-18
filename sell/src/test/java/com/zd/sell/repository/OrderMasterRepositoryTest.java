package com.zd.sell.repository;

import com.zd.sell.entity.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by Unruly Wind on 2019/4/6/006.
 *
 * @author BlueMelancholy
 * @desc:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMasterRepositoryTest {
	private static final String OPENID = "123123";
	@Autowired
	private OrderMasterRepository orderMasterRepository;
	@Test
	public void test1(){
		OrderMaster orderMaster = new OrderMaster();
		orderMaster.setOrderId("1234567");
		orderMaster.setBuyerName("张三");
		orderMaster.setBuyerPhone("123456789123");
		orderMaster.setBuyerAddress("江苏");
		orderMaster.setBuyerOpenid(OPENID);
		orderMaster.setOrderAmount(new BigDecimal(2.5));
		OrderMaster result = orderMasterRepository.save(orderMaster);
		System.out.println(result);
	}
	@Test
	public void test2(){
		PageRequest pageRequest = PageRequest.of(0,10);
		Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(OPENID,pageRequest);
		System.out.println(orderMasterPage.getContent().get(0));
	}
}