package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Unruly Wind on 2019/1/5/005.
 *
 * @author BlueMelancholy
 * @desc:
 */
public interface ProductCategoryDao {
	/**
	 * 通过shopId查询商品类别信息
	 *
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> queryProductCategoryList(long shopId);

	/**
	 * 批量插入店铺类别
	 *
	 * @param productCategoryList
	 * @return
	 */
	int batchInsertProductCategory(List<ProductCategory> productCategoryList);

	/**
	 * 根据商品类别Id和店铺类别Id删除商品类别
	 *
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 */
	int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);
}
