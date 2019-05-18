package com.zd.sell.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Unruly Wind on 2019/4/6/006.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Data
public class CartDTO {
	/** 商品Id. */
	private String productId;
	/** 数量. */
	private Integer productQuantity;

	public CartDTO() {
	}

	public CartDTO(String productId, Integer productQuantity) {
		this.productId = productId;
		this.productQuantity = productQuantity;
	}
}
