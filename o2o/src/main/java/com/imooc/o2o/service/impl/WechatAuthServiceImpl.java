package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.PersonInfoDao;
import com.imooc.o2o.dao.WechatAuthDao;
import com.imooc.o2o.dto.WechatAuthExecution;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.WeChatAuth;
import com.imooc.o2o.enums.WechatAuthStateEnum;
import com.imooc.o2o.exceptions.WechatAuthOperateException;
import com.imooc.o2o.service.WechatAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Unruly Wind on 2019/2/13/013.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Service
@SuppressWarnings("SpringJavaAutowiringInspection")
public class WechatAuthServiceImpl implements WechatAuthService {
	private static Logger log = LoggerFactory.getLogger(WechatAuthServiceImpl.class);
	@Autowired
	private WechatAuthDao wechatAuthDao;
	@Autowired
	private PersonInfoDao personInfoDao;

	/**
	 * 通过openId查找平台对应的微信帐号
	 *
	 * @param openId
	 * @return
	 */
	@Override
	public WeChatAuth getWechatAuthByOpenId(String openId) {
		return wechatAuthDao.queryWechatInfoByOpenId(openId);
	}

	/**
	 * 注册本平台的微信帐号
	 *
	 * @param wechatAuth
	 * @return
	 * @throws RuntimeException
	 */
	@Override
	@Transactional
	public WechatAuthExecution register(WeChatAuth wechatAuth) throws WechatAuthOperateException {
		if (wechatAuth == null || wechatAuth.getOpenId() == null) {
			return new WechatAuthExecution(WechatAuthStateEnum.NULL_AUTH_INFO);
		}
		try {
			//设置创建时间
			wechatAuth.setCreateTime(new Date());
			//如果微信帐号里夹带着用户信息并且用户Id为空，则认为该用户第一次使用平台(且通过微信登录)
			//则自动创建用户信息
			if (wechatAuth.getPersonInfo() != null && wechatAuth.getPersonInfo().getUserId() == null) {
				try {
					wechatAuth.getPersonInfo().setCreateTime(new Date());
					wechatAuth.getPersonInfo().setEnableStatus(1);
					PersonInfo personInfo = wechatAuth.getPersonInfo();
					int effectedNum = personInfoDao.insertPersonInfo(personInfo);
					wechatAuth.setPersonInfo(personInfo);
					if (effectedNum <= 0) {
						throw new WechatAuthOperateException("添加用户信息失败");
					}
				} catch (Exception e) {
					log.error("insertPersonInfo error:" + e.toString());
					throw new WechatAuthOperateException("insertPersonInfo error: " + e.getMessage());
				}
			}
			//创建专属于本平台的微信帐号
			int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);
			if (effectedNum <= 0) {
				throw new WechatAuthOperateException("帐号创建失败");
			} else {
				return new WechatAuthExecution(WechatAuthStateEnum.SUCCESS, wechatAuth);
			}
		} catch (Exception e) {
			log.error("insertWechatAuth error:" + e.toString());
			throw new WechatAuthOperateException("insertWechatAuth error: " + e.getMessage());
		}
	}
}
