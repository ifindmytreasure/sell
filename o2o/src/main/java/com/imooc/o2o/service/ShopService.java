package com.imooc.o2o.service;

import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.exceptions.ShopOperateException;

/**
 * Created by Unruly Wind on 2018/12/25/025.
 *
 * @author BlueMelancholy
 * @desc:店铺的Service接口
 */
public interface ShopService {
	/**
	 * 添加店铺基本信息，包括店铺的图片
	 *
	 * @param shop
	 * @param thumbnail
	 * @return
	 * @throws ShopOperateException
	 */
	ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperateException;

	/**
	 * 更新店铺信息，包括图片流的处理
	 *
	 * @param shop
	 * @param thumbnail
	 * @return
	 * @throws ShopOperateException
	 */
	ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperateException;

	/**
	 * 通过shopId获取店铺信息
	 *
	 * @param shopId
	 * @return
	 */
	Shop getShopById(Long shopId);

	/**
	 * 根据shopCondition分页返回店铺列表
	 *
	 * @param shopCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
}
