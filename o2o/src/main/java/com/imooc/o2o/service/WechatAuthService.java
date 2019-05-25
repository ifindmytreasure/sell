package com.imooc.o2o.service;

import com.imooc.o2o.dto.WechatAuthExecution;
import com.imooc.o2o.entity.WeChatAuth;
import com.imooc.o2o.exceptions.WechatAuthOperateException;

/**
 * Created by Unruly Wind on 2019/2/13/013.
 *
 * @author BlueMelancholy
 * @desc:
 */
public interface WechatAuthService {
	/**
	 * 通过openId查找平台对应的微信帐号
	 *
	 * @param openId
	 * @return
	 */
	WeChatAuth getWechatAuthByOpenId(String openId);

	/**
	 * 注册本平台的微信帐号
	 *
	 * @param wechatAuth
	 * @return
	 * @throws RuntimeException
	 */
	WechatAuthExecution register(WeChatAuth wechatAuth) throws WechatAuthOperateException;

}
