package com.imooc.o2o.enums;

/**
 * Created by Unruly Wind on 2019/2/13/013.
 *
 * @author BlueMelancholy
 * @desc:
 */
public enum WechatAuthStateEnum {
	LOGINFAIL(-1, "openId输入有误"), SUCCESS(0, "操作成功"), NULL_AUTH_INFO(-1006, "注册信息为空");
	private int state;
	private String stateInfo;

	WechatAuthStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static WechatAuthStateEnum stateOf(int state) {
		for (WechatAuthStateEnum wechatAuthStateEnum : values()) {
			if (wechatAuthStateEnum.getState() == state) {
				return wechatAuthStateEnum;
			}
		}
		return null;
	}
}
