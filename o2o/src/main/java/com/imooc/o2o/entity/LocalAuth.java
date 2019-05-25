package com.imooc.o2o.entity;

import java.util.Date;

/**
 * Created by Unruly Wind on 2018/12/22/022.
 *
 * @desc:本地账号
 */
public class LocalAuth {
	private Long localAuthId;
	private String username;
	private String password;
	private Date createTime;
	private Date lastEditTime;
	private PersonInfo personInfo;

	public LocalAuth() {
	}

	public LocalAuth(Long localAuthId, String username, String password, Date createTime, Date lastEditTime, PersonInfo personInfo) {
		this.localAuthId = localAuthId;
		this.username = username;
		this.password = password;
		this.createTime = createTime;
		this.lastEditTime = lastEditTime;
		this.personInfo = personInfo;
	}

	public Long getLocalAuthId() {
		return localAuthId;
	}

	public void setLocalAuthId(Long localAuthId) {
		this.localAuthId = localAuthId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public PersonInfo getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}
}
