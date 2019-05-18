package com.zd.sell.service.impl;

import com.zd.sell.entity.ProductInfo;
import com.zd.sell.enums.ProductStatusEnum;
import com.zd.sell.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by Unruly Wind on 2019/4/5/005.
 *
 * @author BlueMelancholy
 * @desc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
	@Autowired
	private ProductService productService;
	@Test
	public void findOne() {
		Optional<ProductInfo> productInfoOptional = productService.findOne("123456");
		ProductInfo productInfo = productInfoOptional.get();
		System.out.println(productInfo);
	}

	@Test
	public void findUpAll() {
		List<ProductInfo> productInfoList = productService.findUpAll();
		Assert.assertEquals(1,productInfoList.size());
	}

	@Test
	public void findAll() {
		PageRequest pageRequest = PageRequest.of(0,2);
		Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
		System.out.println(productInfoPage.getTotalElements());
		System.out.println(productInfoPage.getContent().get(0));
	}

	@Test
	public void save() {
		ProductInfo productInfo = new ProductInfo();
		productInfo.setProductId("123457");
		productInfo.setProductName("皮皮虾");
		productInfo.setProductPrice(new BigDecimal(3.2));
		productInfo.setProductStock(100);
		productInfo.setProductDescription("很好吃的虾");
		productInfo.setProductIcon("http://xxxxx.jpg");
		productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
		productInfo.setCategoryType(2);
		ProductInfo result = productService.save(productInfo);
	}
}