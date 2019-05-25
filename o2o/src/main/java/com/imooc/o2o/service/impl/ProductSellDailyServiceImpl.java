package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.ProductSellDailyDao;
import com.imooc.o2o.entity.ProductSellDaily;
import com.imooc.o2o.service.ProductSellDailyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Unruly Wind on 2019/3/8/008.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Service
public class ProductSellDailyServiceImpl implements ProductSellDailyService {
	@Autowired
	private ProductSellDailyDao productSellDailyDao;
	private static final Logger logger = LoggerFactory.getLogger(ProductSellDailyServiceImpl.class);

	@Override
	public void dailyCalculate() {
		logger.info("统计已销售的商品");
		productSellDailyDao.insertProductSellDaily();
		logger.info("统计未销售的商品");
		productSellDailyDao.insertDefaultProductSellDaily();
	}

	@Override
	public List<ProductSellDaily> listProductSellDaily(ProductSellDaily productSellDailyCondition, Date beginTime, Date endTime) {
		return productSellDailyDao.queryProductSellDailyList(productSellDailyCondition, beginTime, endTime);
	}

}
