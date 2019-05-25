package com.imooc.o2o.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Unruly Wind on 2019/5/6/006.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Data
public class OrderDetail {

	private Integer detailId;

	private OrderMaster orderMaster;

	private Shop shop;

	/** 商品id. */
	private Integer productId;

	/** 商品名称. */
	private String productName;

	/** 商品单价. */
	private String productPrice;

	/** 商品数量. */
	private Integer productQuantity;

	private Date createTime;
	private Date updateTime;
}
