package com.imooc.o2o.exceptions;

/**
 * Created by Unruly Wind on 2019/5/6/006.
 *
 * @author BlueMelancholy
 * @desc:
 */
public class OrderMasterOperateException extends RuntimeException{
	private static final long serialVersionUID = -4171267207984116950L;

	public OrderMasterOperateException(String message) {
		super(message);
	}
}
