package com.zd.sell.repository;

import com.zd.sell.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
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
public class OrderDetailRepositoryTest {
	@Autowired
	private OrderDetailRepository repository;
	@Test
	public void saveTest() {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setDetailId("12345678");
		orderDetail.setOrderId("1234567");
		orderDetail.setProductIcon("http://xxxx.jpg");
		orderDetail.setProductId("123457");
		orderDetail.setProductName("皮皮虾");
		orderDetail.setProductPrice(new BigDecimal(3.5));
		orderDetail.setProductQuantity(4);

		OrderDetail result = repository.save(orderDetail);
		System.out.println(result);
	}
	@Test
	public void findByOrderId() throws Exception {
		List<OrderDetail> orderDetailList = repository.findByOrderId("1234567");
		Assert.assertNotEquals(0, orderDetailList.size());
	}
}