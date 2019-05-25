package com.imooc.o2o.service;

import com.imooc.o2o.dto.ProductCategoryExecution;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.exceptions.ProductCategoryOperateException;

import java.util.List;

/**
 * Created by Unruly Wind on 2019/1/5/005.
 *
 * @author BlueMelancholy
 * @desc:
 */
public interface ProductCategoryService {
	/**
	 * 通过店铺id获取某个店铺下的所有商品种类列表信息
	 *
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> getProductCategoryList(long shopId);

	/**
	 * 批量添加商品类别
	 *
	 * @param productCategoryList
	 * @return
	 * @throws ProductCategoryExecution
	 */
	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperateException;

	/**
	 * 将此类别下的商品里的类别id置为空，再删除掉该商品类别
	 *
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 * @throws ProductCategoryOperateException
	 */
	ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperateException;
}
