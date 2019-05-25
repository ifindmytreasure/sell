package com.imooc.o2o.exceptions;

/**
 * Created by Unruly Wind on 2019/1/22/022.
 *
 * @author BlueMelancholy
 * @desc:
 */
public class ProductOperateException extends RuntimeException {

	private static final long serialVersionUID = 6033574017955398429L;

	public ProductOperateException(String message) {
		super(message);
	}
}
