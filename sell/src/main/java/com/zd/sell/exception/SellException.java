package com.zd.sell.exception;

import com.zd.sell.enums.ResultEnum;

/**
 * Created by Unruly Wind on 2019/4/6/006.
 *
 * @author BlueMelancholy
 * @desc:
 */
public class SellException extends RuntimeException{
	private Integer code;
	public SellException(ResultEnum resultEnum) {
		super(resultEnum.getMessage());
		this.code = resultEnum.getCode();
	}
	public SellException(Integer code, String message) {
		super(message);
		this.code = code;
	}
}
