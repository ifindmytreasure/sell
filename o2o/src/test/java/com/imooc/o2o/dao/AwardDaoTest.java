package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Award;
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
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AwardDaoTest {
	@Autowired
	private AwardDao awardDao;

	@Test
	public void testAInsertAward() {
		Award award = new Award();
		award.setAwardName("微波炉");
		award.setAwardDesc("耐用");
		award.setAwardImg("测试");
		award.setEnableStatus(1);
		award.setPoint(10);
		award.setPriority(2);
		award.setCreateTime(new Date());
		award.setLastEditTime(new Date());
		award.setShopId(1L);
		awardDao.insertAward(award);
	}

	@Test
	public void testBQueryAwardList() {
		Award awardCondition = new Award();
		awardCondition.setShopId(1L);
		List<Award> awardList = awardDao.queryAwardList(awardCondition, 0, 3);
		System.out.println(awardList.size());
		System.out.println(awardDao.queryAwardCount(awardCondition));
	}

	@Test
	public void testCQuertAwardByAwardId() {
		Award awardCondition = new Award();
		awardCondition.setEnableStatus(1);
		List<Award> awardList = awardDao.queryAwardList(awardCondition, 0, 3);
		System.out.println(awardDao.queryAwardByAwardId(awardList.get(1).getAwardId()).getAwardName());
	}

	@Test
	public void testDUpdateAward() {
		Award award = new Award();
		award.setAwardName("冰箱");
		awardDao.updateAward(award);
	}

	@Test
	public void testEDeleteAward() {
		Award awardCondition = new Award();
		awardCondition.setAwardName("脸");
		// 查询出所有测试奖品并删除
		List<Award> awardList = awardDao.queryAwardList(awardCondition, 0, 2);
		awardDao.deleteAward(awardList.get(0).getAwardId(), awardList.get(0).getShopId());
	}
}
