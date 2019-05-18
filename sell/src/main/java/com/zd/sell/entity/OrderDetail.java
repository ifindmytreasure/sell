package com.zd.sell.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Unruly Wind on 2019/4/5/005.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Entity
@Data
public class OrderDetail {
	@Id
	private String detailId;

	/** 订单id. */
	private String orderId;

	/** 商品id. */
	private String productId;

	/** 商品名称. */
	private String productName;

	/** 商品单价. */
	private BigDecimal productPrice;

	/** 商品数量. */
	private Integer productQuantity;

	/** 商品小图. */
	private String productIcon;
	private Date createTime;
	private Date updateTime;

}
