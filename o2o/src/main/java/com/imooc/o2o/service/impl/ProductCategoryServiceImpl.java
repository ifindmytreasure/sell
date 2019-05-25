package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.ProductCategoryDao;
import com.imooc.o2o.dao.ProductDao;
import com.imooc.o2o.dto.ProductCategoryExecution;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.enums.ProductCategoryStateEnum;
import com.imooc.o2o.exceptions.ProductCategoryOperateException;
import com.imooc.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Unruly Wind on 2019/1/5/005.
 *
 * @author BlueMelancholy
 * @desc:
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	@Autowired
	private ProductCategoryDao productCategoryDao;
	@Autowired
	private ProductDao productDao;

	@Override
	public List<ProductCategory> getProductCategoryList(long shopId) {
		return productCategoryDao.queryProductCategoryList(shopId);
	}

	/**
	 * 批量添加商品类别
	 *
	 * @param productCategoryList
	 * @return
	 * @throws ProductCategoryExecution
	 */
	@Override
	public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperateException {
		if (productCategoryList != null && productCategoryList.size() > 0) {
			try {
				for (ProductCategory list : productCategoryList) {
					list.setCreateTime(new Date());
				}
				int effectNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
				if (effectNum > 0) {
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
				} else {
					throw new ProductCategoryOperateException("店铺类别创建失败");
				}
			} catch (Exception e) {
				throw new ProductCategoryOperateException("batchAddProductCategory error:" + e.getMessage());
			}
		} else {
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
		}
	}

	/**
	 * 将此类别下的商品里的类别id置为空，再删除掉该商品类别
	 *
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 * @throws ProductCategoryOperateException
	 */
	@Override
	@Transactional
	public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperateException {
		//解除
		try {
			int effectedNum = productDao.updateProductCategoryToNull(productCategoryId);
			if (effectedNum <= 0) {
				throw new ProductCategoryOperateException("商品类别置空失败");
			}
		} catch (Exception e) {
			throw new ProductCategoryOperateException("deleteProductCategory error:" + e.toString());
		}
		try {
			int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
			if (effectedNum <= 0) {
				throw new ProductCategoryOperateException("商品类别删除失败");
			} else {
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
			}
		} catch (Exception e) {
			throw new ProductCategoryOperateException("deleteProductCategory error:" + e.getMessage());
		}
	}
}
