package com.imooc.sexy.domain;

import java.math.BigDecimal;
import java.util.Date;

public interface IUser {
	Integer getUserId();

	void setUserId(Integer userId);

	String getUserName();

	void setUserName(String userName);

	Date getBirth();

	void setBirth(Date birth);

	BigDecimal getSalary();

	void setSalary(BigDecimal salary);

	Integer getAge();

	void setAge(Integer age);
}
