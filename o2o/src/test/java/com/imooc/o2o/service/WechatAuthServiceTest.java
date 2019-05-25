package com.imooc.o2o.service;

import com.imooc.o2o.dto.WechatAuthExecution;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.WeChatAuth;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by Unruly Wind on 2019/2/13/013.
 *
 * @author BlueMelancholy
 * @desc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatAuthServiceTest {
	@Autowired
	private WechatAuthService wechatAuthService;

	@Test
	public void testRegister() {
		WeChatAuth weChatAuth = new WeChatAuth();
		PersonInfo personInfo = new PersonInfo();
		String openId = "wangritian";
		personInfo.setUserId(1L);
		personInfo.setCreateTime(new Date());
		personInfo.setName("张三");
		personInfo.setGender("男");
		personInfo.setEnableStatus(1);
		personInfo.setUserType(1);
		weChatAuth.setPersonInfo(personInfo);
		weChatAuth.setOpenId(openId);
		weChatAuth.setCreateTime(new Date());
		WechatAuthExecution wechatAuthExecution = wechatAuthService.register(weChatAuth);
		System.out.println(wechatAuthExecution.getStateInfo());
	}

	@Test
	public void testQueryWechatAuthById() {
		String openId = "zd";
		WeChatAuth weChatAuth = wechatAuthService.getWechatAuthByOpenId(openId);
		System.out.println(weChatAuth.getPersonInfo().getName());
	}
}
