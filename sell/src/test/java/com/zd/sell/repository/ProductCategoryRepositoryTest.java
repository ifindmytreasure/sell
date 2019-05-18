package com.zd.sell.repository;

import com.zd.sell.entity.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by Unruly Wind on 2019/4/4/004.
 *
 * @author BlueMelancholy
 * @desc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	@Test
	public void test1(){
		System.out.println(productCategoryRepository.findById(1));
	}
	@Test
	@Transactional
	public void test2(){
		Optional<ProductCategory> productCategoryOptional = productCategoryRepository.findById(1);
		ProductCategory productCategory = productCategoryOptional.get();
		productCategory.setCategoryType(14);
		productCategoryRepository.save(productCategory);
	}
	@Test
	public void test3(){
		List<Integer> integers = Arrays.asList(1,2,3,4,12,13);
		List<ProductCategory> productCategories = productCategoryRepository.findByCategoryTypeIn(integers);
		System.out.println(productCategories.size());
	}
}