package com.imooc.o2o.exceptions;

/**
 * Created by Unruly Wind on 2019/2/13/013.
 *
 * @author BlueMelancholy
 * @desc:
 */
public class WechatAuthOperateException extends RuntimeException {
	private static final long serialVersionUID = 1622384397632103916L;

	public WechatAuthOperateException(String message) {
		super(message);
	}
}
