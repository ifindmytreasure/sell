package com.imooc.o2o.exceptions;

/**
 * Created by Unruly Wind on 2018/12/25/025.
 *
 * @author BlueMelancholy
 * @desc:店铺操作异常类,继承了RuntimeException
 */
public class ShopOperateException extends RuntimeException {
	private static final long serialVersionUID = 3790761078610310219L;

	public ShopOperateException(String errorMessage) {
		super(errorMessage);
	}
}
