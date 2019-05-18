package com.imooc.sexy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * Created by Unruly Wind on 2019/5/11/011.
 *
 * @author BlueMelancholy
 * @desc:
 */

@Data
public class User implements Serializable {
	/**
	 * userId
	 */
	private Integer userId;

	/**
	 * userName
	 */
	private String userName;

	/**
	 * birth
	 */
	private Date birth;

	/**
	 * 用户薪水
	 */
	private BigDecimal salary;

	/**
	 * 用户年龄
	 */
	private Integer userAge;

	private static final long serialVersionUID = 1L;
}