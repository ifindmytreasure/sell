package com.imooc.o2o.service;

import com.imooc.o2o.dto.ShopAuthMapExecution;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Unruly Wind on 2019/3/6/006.
 *
 * @author BlueMelancholy
 * @desc:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ShopAuthMapServiceTest {
	@Autowired
	private ShopAuthMapService shopAuthMapService;

	@Test
	public void testQueryById() {
		ShopAuthMapExecution shopAuthMapExecution = shopAuthMapService.listShopAuthMapByShopId(1L, 1, 10);
		System.out.println(shopAuthMapExecution.getCount());
		System.out.println(shopAuthMapExecution.getShopAuthMapList().toString());
	}
}
