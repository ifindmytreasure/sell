package com.imooc.o2o.dto;

import com.imooc.o2o.entity.WeChatAuth;
import com.imooc.o2o.enums.WechatAuthStateEnum;

import java.util.List;

/**
 * Created by Unruly Wind on 2019/2/13/013.
 *
 * @author BlueMelancholy
 * @desc:
 */
public class WechatAuthExecution {
	private int state;
	private String stateInfo;
	private int count;
	private WeChatAuth weChatAuth;
	private List<WeChatAuth> weChatAuthList;

	// 失败的构造器
	public WechatAuthExecution(WechatAuthStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 成功的构造器
	public WechatAuthExecution(WechatAuthStateEnum stateEnum, WeChatAuth weChatAuth) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.weChatAuth = weChatAuth;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public WeChatAuth getWeChatAuth() {
		return weChatAuth;
	}

	public void setWeChatAuth(WeChatAuth weChatAuth) {
		this.weChatAuth = weChatAuth;
	}

	public List<WeChatAuth> getWeChatAuthList() {
		return weChatAuthList;
	}

	public void setWeChatAuthList(List<WeChatAuth> weChatAuthList) {
		this.weChatAuthList = weChatAuthList;
	}
}
