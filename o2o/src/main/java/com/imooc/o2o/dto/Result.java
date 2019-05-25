package com.imooc.o2o.dto;

/**
 * Created by Unruly Wind on 2019/1/5/005.
 *
 * @author BlueMelancholy
 * @desc: 商品类别信息返回到前台的dto对象
 */
public class Result<T> {
	private boolean success;//是否成功标识
	private T data;
	private String errMsg;//错误信息
	private int errCode;//错误码

	public Result() {
	}

	public Result(boolean success, T data) {
		this.success = success;
		this.data = data;
	}

	public Result(boolean success, String errMsg, int errCode) {
		this.success = success;
		this.errMsg = errMsg;
		this.errCode = errCode;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}
}
