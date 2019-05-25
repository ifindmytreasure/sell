package com.imooc.o2o.dao;

import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.UserProductMap;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.soap.Addressing;
import java.util.Date;
import java.util.List;

/**
 * Created by Unruly Wind on 2019/3/4/004.
 *
 * @author BlueMelancholy
 * @desc:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserProductMapDaoTest {
	@Autowired
	private UserProductMapDao userProductMapDao;

	@Test
	public void testAInsertUserProductMap() {
		// 创建用户商品映射信息1
		UserProductMap userProductMap = new UserProductMap();
		PersonInfo customer = new PersonInfo();
		customer.setUserId(2L);
		userProductMap.setUser(customer);
		userProductMap.setOperator(customer);
		Product product = new Product();
		product.setProductId(1L);
		userProductMap.setProduct(product);
		Shop shop = new Shop();
		shop.setShopId(1L);
		userProductMap.setShop(shop);
		userProductMap.setCreateTime(new Date());
		System.out.println(userProductMapDao.insertUserProductMap(userProductMap));
		// 创建用户商品映射信息2
		UserProductMap userProductMap2 = new UserProductMap();
		userProductMap2.setUser(customer);
		userProductMap2.setOperator(customer);
		Product product2 = new Product();
		product2.setProductId(2L);
		userProductMap2.setProduct(product2);
		Shop shop2 = new Shop();
		shop2.setShopId(1L);
		userProductMap2.setShop(shop2);
		userProductMap2.setCreateTime(new Date());
		userProductMapDao.insertUserProductMap(userProductMap2);
		// 创建用户商品映射信息3
		UserProductMap userProductMap3 = new UserProductMap();
		userProductMap3.setUser(customer);
		userProductMap3.setOperator(customer);
		Product product3 = new Product();
		product3.setProductId(3L);
		userProductMap3.setProduct(product3);
		Shop shop3 = new Shop();
		shop3.setShopId(1L);
		userProductMap3.setShop(shop3);
		userProductMap3.setCreateTime(new Date());
		userProductMapDao.insertUserProductMap(userProductMap3);
	}

	/**
	 * 测试查询功能
	 *
	 * @throws Exception
	 */
	@Test
	public void testBQueryUserProductMap() throws Exception {
		UserProductMap userProductMap = new UserProductMap();
		PersonInfo customer = new PersonInfo();
		// 按顾客名字模糊查询
		customer.setName("小");
		userProductMap.setUser(customer);
		List<UserProductMap> userProductMapList = userProductMapDao.queryUserProductMapList(userProductMap, 0, 3);
		System.out.println(userProductMapList.size());
		int count = userProductMapDao.queryUserProductMapCount(userProductMap);
		System.out.println(count);
		// 叠加店铺去查询
		Shop shop = new Shop();
		shop.setShopId(1L);
		userProductMap.setShop(shop);
		userProductMapList = userProductMapDao.queryUserProductMapList(userProductMap, 0, 3);
		System.out.println(userProductMapList.size());
		count = userProductMapDao.queryUserProductMapCount(userProductMap);
		System.out.println(count);
	}
}
