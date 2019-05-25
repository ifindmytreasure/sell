package com.imooc.o2o.exceptions;

/**
 * Created by Unruly Wind on 2019/2/15/015.
 *
 * @author BlueMelancholy
 * @desc:
 */
public class LocalAuthOperateException extends RuntimeException {
	private static final long serialVersionUID = -1890540449648595769L;

	public LocalAuthOperateException(String message) {
		super(message);
	}
}
