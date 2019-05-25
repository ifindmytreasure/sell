package com.imooc.o2o.entity;

import java.util.Date;

/**
 * Created by Unruly Wind on 2018/12/22/022.
 *
 * @desc:用户信息实体类
 */
public class PersonInfo {
	private long userId;
	private String name;
	//用户头像
	private String profileImg;
	private String email;
	private String gender;
	//1.顾客 2.商家 3.超级管理员  权限逐级递增
	private Integer enableStatus;
	private Integer userType;
	private Date createTime;
	private Date lastEditTime;

	public PersonInfo() {
	}

	public PersonInfo(Long userId, String name, String profileImg, String email, String gender, Integer enableStatus, Integer userType, Date createTime, Date lastEditTime) {
		this.userId = userId;
		this.name = name;
		this.profileImg = profileImg;
		this.email = email;
		this.gender = gender;
		this.enableStatus = enableStatus;
		this.userType = userType;
		this.createTime = createTime;
		this.lastEditTime = lastEditTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getEnableStatus() {
		return enableStatus;
	}

	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
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
}
