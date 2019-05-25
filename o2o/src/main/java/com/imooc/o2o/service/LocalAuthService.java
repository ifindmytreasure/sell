package com.imooc.o2o.service;

import com.imooc.o2o.dto.LocalAuthExecution;
import com.imooc.o2o.entity.LocalAuth;
import com.imooc.o2o.exceptions.LocalAuthOperateException;

/**
 * Created by Unruly Wind on 2019/2/15/015.
 *
 * @author BlueMelancholy
 * @desc:
 */
public interface LocalAuthService {
	/**
	 * 根据账号和密码获取平台账号信息
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	LocalAuth getLocalAuthByUsernameAndPwd(String username, String password);

	/**
	 * 通过userId获取平台账号信息
	 *
	 * @param userId
	 * @return
	 */
	LocalAuth getLocalAuthByUserId(Long userId);

	/**
	 * 绑定微信，生成平台专属账号
	 *
	 * @param localAuth
	 * @return
	 * @throws LocalAuthOperateException
	 */
	LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperateException;

	/**
	 * 修改平台账号的登录密码
	 *
	 * @param userId
	 * @param username
	 * @param password
	 * @param newPassword
	 * @return
	 * @throws LocalAuthOperateException
	 */
	LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newPassword)
			throws LocalAuthOperateException;
}
