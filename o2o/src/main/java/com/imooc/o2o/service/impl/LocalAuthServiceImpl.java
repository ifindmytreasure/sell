package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.LocalAuthDao;
import com.imooc.o2o.dto.LocalAuthExecution;
import com.imooc.o2o.entity.LocalAuth;
import com.imooc.o2o.enums.LocalAuthStateEnum;
import com.imooc.o2o.exceptions.LocalAuthOperateException;
import com.imooc.o2o.service.LocalAuthService;
import com.imooc.o2o.util.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


/**
 * Created by Unruly Wind on 2019/2/15/015.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Service
@SuppressWarnings("SpringJavaAutowiringInspection")
public class LocalAuthServiceImpl implements LocalAuthService {
	@Autowired
	private LocalAuthDao localAuthDao;
	private static Logger logger = LoggerFactory.getLogger(LocalAuthServiceImpl.class);

	/**
	 * 根据账号和密码获取平台账号信息
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	@Override
	public LocalAuth getLocalAuthByUsernameAndPwd(String username, String password) {
		return localAuthDao.queryLocalByUserNameAndPwd(username, MD5.getMd5(password));
	}

	/**
	 * 通过userId获取平台账号信息
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public LocalAuth getLocalAuthByUserId(Long userId) {
		return localAuthDao.queryLocalByUserId(userId);
	}

	/**
	 * 绑定微信，生成平台专属账号
	 *
	 * @param localAuth
	 * @return
	 * @throws LocalAuthOperateException
	 */
	@Override
	@Transactional
	public LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperateException {
		//空值判断，传入的localAuth账号密码，用户信息特别是userId不能为空，否则直接返回错误
		if (localAuth == null || localAuth.getUsername() == null || localAuth.getPassword() == null || localAuth.getPersonInfo().getUserId() == null) {
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}
		//查询此用户是否已经绑定过平台账号
		LocalAuth tempAuth = localAuthDao.queryLocalByUserId(localAuth.getPersonInfo().getUserId());
		if (tempAuth != null) {
			//如果绑定过则直接退出，以保证平台账号的唯一性
			return new LocalAuthExecution(LocalAuthStateEnum.ONLY_ONE_ACCOUNT);
		}
		try {
			localAuth.setCreateTime(new Date());
			localAuth.setLastEditTime(new Date());
			//对密码进行MD5加密
			localAuth.setPassword(MD5.getMd5(localAuth.getPassword()));
			int effectedNum = localAuthDao.insertLocalAuth(localAuth);
			if (effectedNum <= 0) {
				throw new LocalAuthOperateException("账号绑定失败");
			} else {
				return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS, localAuth);
			}
		} catch (Exception e) {
			throw new LocalAuthOperateException("insertLocalAuth error:" + e.getMessage());
		}
	}

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
	@Override
	@Transactional
	public LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newPassword) throws LocalAuthOperateException {
		if (userId != null && username != null && password != null && newPassword != null && !newPassword.equals(password)) {
			//尝试更新密码，并对密码进行MD5加密
			try {
				int effectedNum = localAuthDao.updateLocalAuth(userId, username, MD5.getMd5(password), MD5.getMd5(newPassword), new Date());
				if (effectedNum <= 0) {
					throw new LocalAuthOperateException("更新密码失败");
				} else {
					return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
				}
			} catch (Exception e) {
				throw new LocalAuthOperateException("更新密码失败" + e.toString());
			}
		} else {
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}
	}
}
