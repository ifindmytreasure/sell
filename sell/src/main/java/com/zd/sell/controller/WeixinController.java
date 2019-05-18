package com.zd.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Unruly Wind on 2019/4/18/018.
 *
 * @author BlueMelancholy
 * @desc:
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {
	//https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf29350047742dfa9&redirect_uri=http:
	// //9wf2cc.natappfree.cc/o2o/wechatlogin/logincheck&role_type=1&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect
	@GetMapping("/auth")
	public void auth(@RequestParam("code")String code){
		log.info("进入auth方法。。。");
		log.info("code={}",code);
	}
}
