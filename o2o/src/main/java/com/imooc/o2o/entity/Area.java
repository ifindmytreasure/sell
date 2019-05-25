package com.imooc.o2o.entity;

import java.util.Date;

/**
 * Created by Unruly Wind on 2018/12/22/022.
 *
 * @desc:区域实体类
 */
public class Area {
	//id号
	private Integer areaId;
	//区域名
	private String areaName;
	//权重，表示优先级
	private Integer priority;
	private Date createTime;
	//更新时间
	private Date lastEditTime;

	public Area() {
	}

	public Area(Integer areaId, String areaName, Integer priority, Date createTime, Date lastEditTime) {
		this.areaId = areaId;
		this.areaName = areaName;
		this.priority = priority;
		this.createTime = createTime;
		this.lastEditTime = lastEditTime;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
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

	@Override
	public String toString() {
		return "Area{" +
				"areaId=" + areaId +
				", areaName='" + areaName + '\'' +
				", priority=" + priority +
				", createTime=" + createTime +
				", lastEditTime=" + lastEditTime +
				'}';
	}
}
