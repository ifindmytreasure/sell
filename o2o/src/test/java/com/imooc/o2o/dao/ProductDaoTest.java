package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.ProductImg;
import com.imooc.o2o.entity.Shop;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Unruly Wind on 2019/1/22/022.
 *
 * @author BlueMelancholy
 * @desc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;

	@Test
	public void testAInsertProduct() throws Exception {
		Shop shop1 = new Shop();
		shop1.setShopId(1L);
		ProductCategory pc1 = new ProductCategory();
		pc1.setProductCategoryId(3L);
		// 初始化三个商品实例并添加进shopId为1的店铺里，
		// 同时商品类别Id也为1
		Product product1 = new Product();
		product1.setProductName("测试1");
		product1.setProductDesc("测试Desc1");
		product1.setImgAddr("test1");
		product1.setPriority(1);
		product1.setEnableStatus(1);
		product1.setCreateTime(new Date());
		product1.setLastEditTime(new Date());
		product1.setShop(shop1);
		product1.setProductCategory(pc1);
		Product product2 = new Product();
		product2.setProductName("测试2");
		product2.setProductDesc("测试Desc2");
		product2.setImgAddr("test2");
		product2.setPriority(2);
		product2.setEnableStatus(0);
		product2.setCreateTime(new Date());
		product2.setLastEditTime(new Date());
		product2.setShop(shop1);
		product2.setProductCategory(pc1);
		Product product3 = new Product();
		product3.setProductName("test3");
		product3.setProductDesc("测试Desc3");
		product3.setImgAddr("test3");
		product3.setPriority(3);
		product3.setEnableStatus(1);
		product3.setCreateTime(new Date());
		product3.setLastEditTime(new Date());
		product3.setShop(shop1);
		product3.setProductCategory(pc1);
		// 判断添加是否成功
		int effectedNum = productDao.insertProduct(product1);
		System.out.println(effectedNum);
		effectedNum = productDao.insertProduct(product2);
		System.out.println(effectedNum);
		effectedNum = productDao.insertProduct(product3);
		System.out.println(effectedNum);
	}

	@Test
	public void testQueryProductList() {
		Product productCondition = new Product();
		List<Product> productList = productDao.queryProductList(productCondition, 0, 3);
		System.out.println(productList.size());
		int count = productDao.queryProductCount(productCondition);
		System.out.println(count);
		productCondition.setProductName("测试");
		productList = productDao.queryProductList(productCondition, 0, 3);
		System.out.println(productDao.queryProductCount(productCondition));
	}

	@Test
	public void testCQueryProductByProductId() {
		Product product = null;
		long productId = 1;
		product = productDao.queryProductById(1);
		System.out.println(product);
		System.out.println(product.getShop().getShopId());
	}

	@Test
	public void testDUpdateProduct() {
		Product product = new Product();
		ProductCategory productCategory = new ProductCategory();
		Shop shop = new Shop();
		shop.setShopId(1L);
		productCategory.setProductCategoryId(14L);
		product.setProductId(4L);
		product.setShop(shop);
		product.setProductCategory(productCategory);
		product.setProductName("雪碧");
		product.setNormalPrice("5");
		System.out.println(productDao.updateProduct(product));
	}
}
