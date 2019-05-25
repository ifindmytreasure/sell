package com.imooc.o2o.dao;

import com.imooc.o2o.entity.WeChatAuth;

/**
 * Created by Unruly Wind on 2019/2/13/013.
 *
 * @author BlueMelancholy
 * @desc:
 */
public interface WechatAuthDao {
	/**
	 * 通过openId查询对应本平台的微信帐号
	 *
	 * @param openId
	 * @return
	 */
	WeChatAuth queryWechatInfoByOpenId(String openId);

	/**
	 * 添加对应本平台的微信帐号
	 *
	 * @param wechatAuth
	 * @return
	 */
	int insertWechatAuth(WeChatAuth wechatAuth);
}
