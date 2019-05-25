package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Unruly Wind on 2018/12/30/030.
 *
 * @author BlueMelancholy
 * @desc: ShopCategoryDao层
 */
public interface ShopCategoryDao {
	List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);
}
