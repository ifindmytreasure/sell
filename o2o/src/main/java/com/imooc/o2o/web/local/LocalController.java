package com.imooc.o2o.web.local;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Unruly Wind on 2019/2/15/015.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Controller
@RequestMapping("/local")
public class LocalController {
	/**
	 * 绑定账号路由
	 *
	 * @return
	 */
	@RequestMapping(value = "/accountbind", method = RequestMethod.GET)
	private String accountBind() {
		return "/local/accountbind";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	private String login() {
		return "/local/login";
	}

	@RequestMapping(value = "/changepsw", method = RequestMethod.GET)
	private String changpsw() {
		return "/local/changepsw";
	}
}
