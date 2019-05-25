package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.UserShopMapDao;
import com.imooc.o2o.dto.UserShopMapExecution;
import com.imooc.o2o.entity.UserShopMap;
import com.imooc.o2o.service.UserShopMapService;
import com.imooc.o2o.util.PageCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Unruly Wind on 2019/3/10/010.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Service
public class UserShopMapServiceImpl implements UserShopMapService {
	@Autowired
	private UserShopMapDao userShopMapDao;

	/**
	 * 根据传入的查询信息分页查询用户积分列表
	 *
	 * @param userShopMapCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@Override
	public UserShopMapExecution listUserShopMap(UserShopMap userShopMapCondition, int pageIndex, int pageSize) {
		// 空值判断
		if (userShopMapCondition != null && pageIndex != -1 && pageSize != -1) {
			// 页转行
			int beginIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
			// 根据传入的查询条件分页返回用户积分列表信息
			List<UserShopMap> userShopMapList = userShopMapDao.queryUserShopMapList(userShopMapCondition, beginIndex,
					pageSize);
			// 返回总数
			int count = userShopMapDao.queryUserShopMapCount(userShopMapCondition);
			UserShopMapExecution ue = new UserShopMapExecution();
			ue.setUserShopMapList(userShopMapList);
			ue.setCount(count);
			return ue;
		} else {
			return null;
		}
	}

	/**
	 * 根据用户Id和店铺Id返回该用户在某个店铺的积分情况
	 *
	 * @param userId
	 * @param shopId
	 * @return
	 */
	@Override
	public UserShopMap getUserShopMap(long userId, long shopId) {
		return userShopMapDao.queryUserShopMap(userId, shopId);
	}
}
