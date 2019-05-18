package com.zd.sell.VO;

import lombok.Data;

/**
 * Created by Unruly Wind on 2019/4/5/005.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Data
public class ResultVO<T> {
	/** 错误码. */
	private Integer code;

	/** 提示信息. */
	private String msg;

	/** 具体内容. */
	private T data;
}
