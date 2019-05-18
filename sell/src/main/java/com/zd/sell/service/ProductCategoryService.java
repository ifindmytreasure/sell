package com.zd.sell.service;

import com.zd.sell.entity.ProductCategory;

import java.util.List;
import java.util.Optional;

/**
 * Created by Unruly Wind on 2019/4/4/004.
 *
 * @author BlueMelancholy
 * @desc:
 */
public interface ProductCategoryService {
	Optional<ProductCategory> findOne(Integer categoryId);

	List<ProductCategory> findAll();

	List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

	ProductCategory save(ProductCategory productCategory);
}
