package com.imooc.o2o.service;

import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;

import java.util.List;

/**
 * Created by Unruly Wind on 2018/12/30/030.
 *
 * @author BlueMelancholy
 * @desc:
 */
public interface ShopCategoryService {
	public static final String SCLISTKEY = "shopcategorylist";

	/**
	 * 根据传入的商铺类别条件查询信息，优先从缓存中获取
	 *
	 * @param shopCategoryCondition
	 * @return
	 */
	List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
