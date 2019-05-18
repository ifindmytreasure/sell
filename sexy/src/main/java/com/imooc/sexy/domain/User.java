package com.imooc.sexy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Unruly Wind on 2019/5/11/011.
 *
 * @author BlueMelancholy
 * @desc:
 */
public class User implements IUser {
	private Integer userId;

	private String userName;

	private Date birth;

	/**
	 * 用户薪水
	 */
	private BigDecimal salary;

	private Integer age;

	@Override
	public Integer getUserId() {
		return userId;
	}

	@Override
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public Date getBirth() {
		return birth;
	}

	@Override
	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public BigDecimal getSalary() {
		return salary;
	}

	@Override
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	@Override
	public Integer getAge() {
		return age;
	}

	@Override
	public void setAge(Integer age) {
		this.age = age;
	}
}
