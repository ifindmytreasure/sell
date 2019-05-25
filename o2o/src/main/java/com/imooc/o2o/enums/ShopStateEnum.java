package com.imooc.o2o.enums;

/**
 * Created by Unruly Wind on 2018/12/25/025.
 *
 * @author BlueMelancholy
 * @desc:店铺状态信息的枚举类
 */
public enum ShopStateEnum {
	/**
	 * values()
	 */
	CHECK(0, "审核中"), OFFLINE(-1, "非法店铺"), SUCCESS(1, "操作成功"), PASS(2, "通过认证"),
	INNER_ERROR(-1001, "内部系统错误"), NULL_SHOPID(-1002, "ShopId为空"), NULL_SHOP(-1003, "shop信息为空");
	private int state;
	private String stateInfo;

	private ShopStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	/**
	 * 传入state返回对应的enum值
	 *
	 * @param state
	 * @return
	 */

	public static ShopStateEnum stateOf(int state) {
		/**
		 * 遍历枚举类,通过values获取所有的枚举
		 */
		for (ShopStateEnum stateEnum : values()) {
			if (stateEnum.getState() == state) {
				return stateEnum;
			}
		}
		return null;
	}
}
