package com.imooc.sexy.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by Unruly Wind on 2019/5/11/011.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Data
public class Product {
	private ProductItem item;
	private List<String> itemName;

}
