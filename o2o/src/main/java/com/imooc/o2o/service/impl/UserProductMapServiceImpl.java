package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.UserProductMapDao;
import com.imooc.o2o.dao.UserShopMapDao;
import com.imooc.o2o.dto.UserProductMapExecution;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.UserProductMap;
import com.imooc.o2o.entity.UserShopMap;
import com.imooc.o2o.enums.UserProductMapStateEnum;
import com.imooc.o2o.exceptions.UserAwardMapOperateException;
import com.imooc.o2o.exceptions.UserProductMapOperateException;
import com.imooc.o2o.service.UserProductMapService;
import com.imooc.o2o.util.PageCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Unruly Wind on 2019/3/8/008.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Service
public class UserProductMapServiceImpl implements UserProductMapService {
	@Autowired
	private UserProductMapDao userProductMapDao;
	@Autowired
	private UserShopMapDao userShopMapDao;

	/**
	 * 通过传入的查询条件分页列出用户消费信息列表
	 *
	 * @param userProductCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@Override
	public UserProductMapExecution listUserProductMap(UserProductMap userProductCondition, Integer pageIndex, Integer pageSize) {
		// 空值判断
		if (userProductCondition != null && pageIndex != null && pageSize != null) {
			// 页转行
			int beginIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
			// 依据查询条件分页取出列表
			List<UserProductMap> userProductMapList = userProductMapDao.queryUserProductMapList(userProductCondition,
					beginIndex, pageSize);
			// 按照同等的查询条件获取总数
			int count = userProductMapDao.queryUserProductMapCount(userProductCondition);
			UserProductMapExecution se = new UserProductMapExecution();
			se.setUserProductMapList(userProductMapList);
			se.setCount(count);
			return se;
		} else {
			return null;
		}
	}

	/**
	 * 添加消费记录
	 *
	 * @param userProductMap
	 * @return
	 * @throws UserAwardMapOperateException
	 */
	@Override
	@Transactional
	public UserProductMapExecution addUserProductMap(UserProductMap userProductMap) throws UserAwardMapOperateException {
		if (userProductMap != null && userProductMap.getUser().getUserId() != null && userProductMap.getOperator().getUserId() != null) {
			userProductMap.setCreateTime(new Date());
			try {
				int effectedNum = userProductMapDao.insertUserProductMap(userProductMap);
				if (effectedNum <= 0) {
					throw new UserAwardMapOperateException("添加消费记录失败");
				}
				if (userProductMap.getPoint() != null && userProductMap.getPoint() > 0) {
					UserShopMap userShopMap = userShopMapDao.queryUserShopMap(userProductMap.getUser().getUserId(), userProductMap.getShop().getShopId());
					if (userShopMap != null && userShopMap.getUserShopId() != null) {
						userShopMap.setPoint(userShopMap.getPoint() + userProductMap.getPoint());
						effectedNum = userShopMapDao.updateUserShopMapPoint(userShopMap);
						if (effectedNum <= 0) {
							throw new UserProductMapOperateException("更新积分信息失败");
						}
					} else {
						userShopMap = compactUserShopMap4Add(userProductMap.getUser().getUserId(), userProductMap.getShop().getShopId(), userProductMap.getPoint());
						effectedNum = userShopMapDao.insertUserShopMap(userShopMap);
						if (effectedNum <= 0) {
							throw new UserProductMapOperateException("积分信息创建失败");
						}
					}
				}
				return new UserProductMapExecution(UserProductMapStateEnum.SUCCESS, userProductMap);
			} catch (Exception e) {
				throw new UserProductMapOperateException("添加授权失败:" + e.toString());
			}
		} else {
			return new UserProductMapExecution(UserProductMapStateEnum.NULL_USERPRODUCT_INFO);
		}
	}

	private UserShopMap compactUserShopMap4Add(Long userId, Long shopId, Integer point) {
		UserShopMap userShopMap = null;
		if (userId != null && shopId != null) {
			userShopMap = new UserShopMap();
			Shop shop = new Shop();
			PersonInfo customer = new PersonInfo();
			shop.setShopId(shopId);
			customer.setUserId(userId);
			userShopMap.setShop(shop);
			userShopMap.setUser(customer);
			userShopMap.setCreateTime(new Date());
			userShopMap.setPoint(point);
		}
		return userShopMap;
	}
}
