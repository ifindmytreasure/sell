package com.imooc.o2o.entity;

import java.util.Date;

/**
 * Created by Unruly Wind on 2018/12/22/022.
 *
 * @desc:微信账号
 */
public class WeChatAuth {
	private Long wechatAuthId;
	//作为微信账号与公众号绑定的唯一标识
	private String openId;
	private Date createTime;
	//用户信息，数据库中用userId记录
	private PersonInfo personInfo;

	public WeChatAuth() {
	}

	public WeChatAuth(Long wechatAuthId, String openId, Date createTime, PersonInfo personInfo) {
		this.wechatAuthId = wechatAuthId;
		this.openId = openId;
		this.createTime = createTime;
		this.personInfo = personInfo;
	}

	public Long getWechatAuthId() {
		return wechatAuthId;
	}

	public void setWechatAuthId(Long wechatAuthId) {
		this.wechatAuthId = wechatAuthId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public PersonInfo getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}
}
