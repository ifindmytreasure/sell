package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Award;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.UserAwardMap;
import org.apache.catalina.User;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by Unruly Wind on 2019/3/3/003.
 *
 * @author BlueMelancholy
 * @desc:
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAwardMapDaoTest {
	@Autowired
	private UserAwardMapDao userAwardMapDao;

	@Test
	public void testAInsertUserAwardMap() {
		// 创建用户奖品映射信息1
		UserAwardMap userAwardMap = new UserAwardMap();
		PersonInfo customer = new PersonInfo();
		customer.setUserId(2L);
		userAwardMap.setUser(customer);
		userAwardMap.setOperator(customer);
		Award award = new Award();
		award.setAwardId(2L);
		userAwardMap.setAward(award);
		Shop shop = new Shop();
		shop.setShopId(1L);
		userAwardMap.setShop(shop);
		userAwardMap.setCreateTime(new Date());
		userAwardMap.setUsedStatus(1);
		userAwardMap.setPoint(10);
		int effectedNum = userAwardMapDao.insertUserAwardMap(userAwardMap);
		System.out.println(effectedNum);
		// 创建用户奖品映射信息2
		UserAwardMap userAwardMap2 = new UserAwardMap();
		PersonInfo customer2 = new PersonInfo();
		customer2.setUserId(2L);
		userAwardMap2.setUser(customer2);
		userAwardMap2.setOperator(customer2);
		Award award2 = new Award();
		award2.setAwardId(2L);
		userAwardMap2.setAward(award2);
		userAwardMap2.setShop(shop);
		userAwardMap2.setCreateTime(new Date());
		userAwardMap2.setUsedStatus(0);
		userAwardMap2.setPoint(10);
		effectedNum = userAwardMapDao.insertUserAwardMap(userAwardMap2);
		System.out.println(effectedNum);
	}

	@Test
	public void testBQueryUserAwardMapList() {
		PersonInfo costumer = new PersonInfo();
		UserAwardMap userAwardMapCondition = new UserAwardMap();
		costumer.setUserId(2L);
		userAwardMapCondition.setUser(costumer);
		List<UserAwardMap> userAwardMapList = userAwardMapDao.queryUserAwardMapList(userAwardMapCondition, 0, 10);
		System.out.println(userAwardMapList.size());
		userAwardMapList.get(0).setUsedStatus(1);
		userAwardMapDao.updateUserAwardMap(userAwardMapList.get(0));
	}

	@Test
	public void TestCQueryUserMapById() {
		long userAwardId = 2;
		UserAwardMap userAwardMap = new UserAwardMap();
		Award award = new Award();
		award.setAwardName("冰");
		userAwardMap.setAward(award);
		System.out.println(userAwardMapDao.queryUserAwardMapCount(userAwardMap));
		System.out.println(userAwardMapDao.queryUserAwardMapById(userAwardMapDao.queryUserAwardMapList(userAwardMap, 0, 3).get(0).getUserAwardId()));
	}

}
