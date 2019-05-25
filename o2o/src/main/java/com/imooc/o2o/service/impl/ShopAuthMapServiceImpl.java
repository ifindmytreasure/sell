package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.ShopAuthMapDao;
import com.imooc.o2o.dto.ShopAuthMapExecution;
import com.imooc.o2o.entity.ShopAuthMap;
import com.imooc.o2o.enums.ShopAuthMapStateEnum;
import com.imooc.o2o.exceptions.ShopAuthMapOperateException;
import com.imooc.o2o.service.ShopAuthMapService;
import com.imooc.o2o.util.PageCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Unruly Wind on 2019/3/6/006.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Service
public class ShopAuthMapServiceImpl implements ShopAuthMapService {
	@Autowired
	private ShopAuthMapDao shopAuthMapDao;

	/**
	 * 根据店铺Id分页显示该店铺的授权信息
	 *
	 * @param shopId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@Override
	public ShopAuthMapExecution listShopAuthMapByShopId(Long shopId, Integer pageIndex, Integer pageSize) {
		if (shopId != null && pageIndex != null && pageSize != null) {
			int beginIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
			List<ShopAuthMap> shopAuthMapList = shopAuthMapDao.queryShopAuthMapListByShopId(shopId, beginIndex, pageSize);
			int count = shopAuthMapDao.queryShopAuthCountByShopId(shopId);
			ShopAuthMapExecution shopAuthMapExecution = new ShopAuthMapExecution();
			shopAuthMapExecution.setCount(count);
			shopAuthMapExecution.setShopAuthMapList(shopAuthMapList);
			return shopAuthMapExecution;
		} else {
			return null;
		}

	}

	/**
	 * 根据shopAuthId返回对应的授权信息
	 *
	 * @param shopAuthId
	 * @return
	 */
	@Override
	public ShopAuthMap getShopAuthMapById(Long shopAuthId) {
		return shopAuthMapDao.queryShopAuthMapById(shopAuthId);
	}

	/**
	 * 添加授权信息
	 *
	 * @param shopAuthMap
	 * @return
	 * @throws ShopAuthMapOperateException
	 */
	@Override
	@Transactional
	public ShopAuthMapExecution addShopAuthMap(ShopAuthMap shopAuthMap) throws ShopAuthMapOperateException {
		if (shopAuthMap != null && shopAuthMap.getShop() != null && shopAuthMap.getShop().getShopId() != null && shopAuthMap.getEmployee() != null && shopAuthMap.getEmployee().getUserId() != null) {
			shopAuthMap.setCreateTime(new Date());
			shopAuthMap.setLastEditTime(new Date());
			shopAuthMap.setEnableStatus(1);
			try {
				// 添加授权信息
				int effectedNum = shopAuthMapDao.insertShopAuthMap(shopAuthMap);
				if (effectedNum <= 0) {
					throw new ShopAuthMapOperateException("添加授权失败");
				}
				return new ShopAuthMapExecution(ShopAuthMapStateEnum.SUCCESS, shopAuthMap);
			} catch (Exception e) {
				throw new ShopAuthMapOperateException("添加授权失败:" + e.toString());
			}
		} else {
			return new ShopAuthMapExecution(ShopAuthMapStateEnum.NULL_SHOPAUTH_INFO);
		}
	}

	/**
	 * 更新授权信息，包括职位，状态等
	 *
	 * @param shopAuthMap@return
	 * @throws ShopAuthMapOperateException
	 */
	@Override
	@Transactional
	public ShopAuthMapExecution modifyShopAuthMap(ShopAuthMap shopAuthMap) throws ShopAuthMapOperateException {
		if (shopAuthMap == null || shopAuthMap.getShopAuthId() == null) {
			return new ShopAuthMapExecution(ShopAuthMapStateEnum.NULL_SHOPAUTH_ID);
		} else {
			try {
				shopAuthMap.setLastEditTime(new Date());
				int effectedNum = shopAuthMapDao.updateShopAuthMap(shopAuthMap);
				if (effectedNum <= 0) {
					return new ShopAuthMapExecution(ShopAuthMapStateEnum.INNER_ERROR);
				} else {// 创建成功
					return new ShopAuthMapExecution(ShopAuthMapStateEnum.SUCCESS, shopAuthMap);
				}
			} catch (Exception e) {
				throw new ShopAuthMapOperateException("modifyShopAuthMap error: " + e.getMessage());
			}
		}
	}
}
