package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.ShopAuthMapDao;
import com.imooc.o2o.dao.ShopDao;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopAuthMap;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exceptions.ShopOperateException;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PageCalculator;
import com.imooc.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by Unruly Wind on 2018/12/25/025.
 * ShopOperateException继承了RuntimeException
 *
 * @author BlueMelancholy
 * @desc:店铺Servic的实现类
 */
@Service
@SuppressWarnings("SpringJavaAutowiringInspection")
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private ShopAuthMapDao shopAuthMapDao;

	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperateException {
		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			//给店铺添加初始化信息
			shop.setEnableStatus(ShopStateEnum.CHECK.getState());
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			//添加店铺信息
			int effectedNum = shopDao.insertShop(shop);
			//证明已获取到自增主键的值
			System.out.println("店铺id号：" + shop.getShopId());
			if (effectedNum <= 0) {
				throw new ShopOperateException("店铺创建失败");
			} else {
				if (thumbnail.getImage() != null) {
					//转储图片
					try {
						addShopImg(shop, thumbnail);
					} catch (Exception e) {
						throw new ShopOperateException("addShopImg error:" + e.getMessage());
					}
					//更新店铺的图片地址
					effectedNum = shopDao.updateShop(shop);
					if (effectedNum <= 0) {
						throw new ShopOperateException("更新图片地址失败");
					}
					// 执行增加shopAuthMap操作
					ShopAuthMap shopAuthMap = new ShopAuthMap();
					shopAuthMap.setEmployee(shop.getOwner());
					shopAuthMap.setShop(shop);
					shopAuthMap.setTitle("店家");
					shopAuthMap.setTitleFlag(0);
					shopAuthMap.setCreateTime(new Date());
					shopAuthMap.setLastEditTime(new Date());
					shopAuthMap.setEnableStatus(1);
					try {
						effectedNum = shopAuthMapDao.insertShopAuthMap(shopAuthMap);
						if (effectedNum <= 0) {
							throw new ShopOperateException("授权创建失败");
						}
					} catch (Exception e) {
						throw new ShopOperateException("insertShopAuthMap error: " + e.getMessage());
					}
				}
			}
		} catch (Exception e) {
			throw new ShopOperateException("addShop error" + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}

	@Override
	public ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperateException {
		if (shop == null || shop.getShopId() == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		} else {
			try {
				//判断是否需要处理图片，即判断图片是否为空
				if (thumbnail.getImage() != null && thumbnail.getimageName() != null && !"".equals(thumbnail.getimageName())) {
					Shop tempShop = shopDao.queryByShopId(shop.getShopId());
					if (tempShop.getShopImg() != null) {
						ImageUtil.deleteFileOrPath(tempShop.getShopImg());
					}
					addShopImg(shop, thumbnail);
				}
				//更新店铺信息
				shop.setLastEditTime(new Date());
				int effectNum = shopDao.updateShop(shop);
				if (effectNum <= 0) {
					return new ShopExecution(ShopStateEnum.INNER_ERROR);
				} else {
					shop = shopDao.queryByShopId(shop.getShopId());
					return new ShopExecution(ShopStateEnum.SUCCESS, shop);
				}
			} catch (Exception e) {
				throw new ShopOperateException("modifyShop error" + e.getMessage());
			}
		}
	}

	@Override
	public Shop getShopById(Long shopId) {
		return shopDao.queryByShopId(shopId);
	}

	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
		/**
		 * 数据库中遍历每一行，而前台页面一页页遍历，因此要将pageIndex转成rowIndex
		 */
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
		ShopExecution shopExecution = new ShopExecution();
		int count = shopDao.queryShopCount(shopCondition);
		if (shopList != null) {
			shopExecution.setShopList(shopList);
			shopExecution.setCount(count);
		} else {
			shopExecution.setState(ShopStateEnum.INNER_ERROR.getState());
		}
		return shopExecution;
	}

	private void addShopImg(Shop shop, ImageHolder thumbnail) {
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		//返回的是图片的相对路径
		String shopImgAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		shop.setShopImg(shopImgAddr);
	}
}
