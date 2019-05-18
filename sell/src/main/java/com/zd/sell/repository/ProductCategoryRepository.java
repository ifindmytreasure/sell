package com.zd.sell.repository;

import com.zd.sell.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Unruly Wind on 2019/4/4/004.
 *
 * @author BlueMelancholy
 * @desc:
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
	List<ProductCategory> findByCategoryTypeIn(List<Integer> typeList);
}
