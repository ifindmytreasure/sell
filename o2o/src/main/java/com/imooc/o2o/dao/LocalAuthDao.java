package com.imooc.o2o.dao;

import com.imooc.o2o.entity.LocalAuth;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * Created by Unruly Wind on 2019/2/15/015.
 *
 * @author BlueMelancholy
 * @desc:
 */
public interface LocalAuthDao {
	/**
	 * 通过账号和密码查询本地用户，登录所用
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	LocalAuth queryLocalByUserNameAndPwd(@Param("username") String username, @Param("password") String password);

	/**
	 * 通过用户Id查询对应localauth
	 *
	 * @param userId
	 * @return
	 */
	LocalAuth queryLocalByUserId(long userId);

	/**
	 * 添加平台账号
	 *
	 * @param localAuth
	 * @return
	 */
	int insertLocalAuth(LocalAuth localAuth);

	/**
	 * 通过userId，username,password更
	 *
	 * @param userId
	 * @param username
	 * @param password
	 * @param newPassword
	 * @param lastEditTime
	 * @return
	 */
	int updateLocalAuth(@Param("userId") Long userId, @Param("username") String username, @Param("password") String password, @Param("newPassword") String newPassword, @Param("lastEditTime") Date lastEditTime);
}
