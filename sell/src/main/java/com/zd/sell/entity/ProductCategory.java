package com.zd.sell.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Unruly Wind on 2019/4/4/004.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Entity
@DynamicUpdate
@Data
public class ProductCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;

	/** 类目名字. */
	private String categoryName;

	/** 类目编号. */
	private Integer categoryType;

	private Date createTime;

	private Date updateTime;

	public ProductCategory() {
	}

	public ProductCategory(String categoryName, Integer categoryType, Date createTime, Date updateTime) {
		this.categoryName = categoryName;
		this.categoryType = categoryType;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
}
