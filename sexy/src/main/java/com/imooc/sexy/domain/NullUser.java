package com.imooc.sexy.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Unruly Wind on 2019/5/11/011.
 *
 * @author BlueMelancholy
 * @desc:
 */
public class NullUser implements IUser {
	@Override
	public Integer getUserId() {
		return null;
	}

	@Override
	public void setUserId(Integer userId) {

	}

	@Override
	public String getUserName() {
		return null;
	}

	@Override
	public void setUserName(String userName) {

	}

	@Override
	public Date getBirth() {
		return null;
	}

	@Override
	public void setBirth(Date birth) {

	}

	@Override
	public BigDecimal getSalary() {
		return null;
	}

	@Override
	public void setSalary(BigDecimal salary) {

	}

	@Override
	public Integer getAge() {
		return null;
	}

	@Override
	public void setAge(Integer age) {

	}
}
