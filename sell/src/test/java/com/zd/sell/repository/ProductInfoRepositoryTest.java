package com.zd.sell.repository;

import com.zd.sell.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Unruly Wind on 2019/4/4/004.
 *
 * @author BlueMelancholy
 * @desc:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoRepositoryTest {
	@Autowired
	private ProductInfoRepository repository;
	@Test
	public void saveTest() {
		ProductInfo productInfo = new ProductInfo();
		productInfo.setProductId("123456");
		productInfo.setProductName("皮蛋粥");
		productInfo.setProductPrice(new BigDecimal(3.2));
		productInfo.setProductStock(100);
		productInfo.setProductDescription("很好喝的粥");
		productInfo.setProductIcon("http://xxxxx.jpg");
		productInfo.setProductStatus(0);
		productInfo.setCategoryType(1);

		ProductInfo result = repository.save(productInfo);
		Assert.assertNotNull(result);
	}
	@Test
	public void findByProductStatus() throws Exception {

		List<ProductInfo> productInfoList = repository.findAllByAndProductStatus(0);
		Assert.assertEquals(1, productInfoList.size());
	}
}