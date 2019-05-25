package com.imooc.o2o.exceptions;

/**
 * Created by Unruly Wind on 2019/1/8/008.
 *
 * @author BlueMelancholy
 * @desc:
 */
public class ProductCategoryOperateException extends RuntimeException {

	private static final long serialVersionUID = 4255016832429499113L;

	public ProductCategoryOperateException(String message) {
		super(message);
	}
}
