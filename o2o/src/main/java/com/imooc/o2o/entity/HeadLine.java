package com.imooc.o2o.entity;

import java.util.Date;

/**
 * Created by Unruly Wind on 2018/12/22/022.
 *
 * @desc:头条信息
 */
public class HeadLine {
	private Long lineId;
	private String lineName;
	//链接
	private String lineLink;
	private String lineImg;
	//头条展示优先级
	private Integer priority;
	//0.不可用 1.可用
	private Integer enableStatus;
	private Date createTime;
	private Date lastEditTime;

	public HeadLine() {
	}

	public HeadLine(Long lineId, String lineName, String lineLink, String lineImg, Integer priority, Integer enableStatus, Date createTime, Date lastEditTime) {
		this.lineId = lineId;
		this.lineName = lineName;
		this.lineLink = lineLink;
		this.lineImg = lineImg;
		this.priority = priority;
		this.enableStatus = enableStatus;
		this.createTime = createTime;
		this.lastEditTime = lastEditTime;
	}

	public Long getLineId() {
		return lineId;
	}

	public void setLineId(Long lineId) {
		this.lineId = lineId;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getLineLink() {
		return lineLink;
	}

	public void setLineLink(String lineLink) {
		this.lineLink = lineLink;
	}

	public String getLineImg() {
		return lineImg;
	}

	public void setLineImg(String lineImg) {
		this.lineImg = lineImg;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getEnableStatus() {
		return enableStatus;
	}

	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
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
