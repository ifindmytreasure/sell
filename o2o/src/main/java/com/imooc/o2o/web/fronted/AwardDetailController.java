package com.imooc.o2o.web.fronted;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.imooc.o2o.entity.Award;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.UserAwardMap;
import com.imooc.o2o.service.AwardService;
import com.imooc.o2o.util.CodeUtil;
import com.imooc.o2o.util.HttpServletRequestUtil;
import com.imooc.o2o.util.ShortNetAddressUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Unruly Wind on 2019/3/13/013.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Controller
@RequestMapping("/fronted")
public class AwardDetailController {
	@Autowired
	private AwardService awardService;

	@RequestMapping(value = "/listawarddetailpageinfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listAwardDetailPageInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = Collections.synchronizedMap(new HashMap<>(10));
		long awardId = HttpServletRequestUtil.getLong(request, "awardId");
		Award award = null;
		if (awardId != -1) {
			award = awardService.getAwardById(awardId);
			//2.0新增
			PersonInfo user = (PersonInfo) request.getSession().getAttribute(
					"user");
			if (user == null) {
				modelMap.put("needQRCode", false);
			} else {
				modelMap.put("needQRCode", true);
			}
			modelMap.put("success", true);
			modelMap.put("award", award);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty awardId");
		}
		return modelMap;
	}

	// 微信获取用户信息的api前缀
	private static String urlPrefix;
	// 微信获取用户信息的api中间部分
	private static String urlMiddle;
	// 微信获取用户信息的api后缀
	private static String urlSuffix;
	// 微信回传给的响应添加顾客商品映射信息的url
	private static String exchangeUrl;

	@Value("${wechat.prefix}")
	public void setUrlPrefix(String urlPrefix) {
		AwardDetailController.urlPrefix = urlPrefix;
	}

	@Value("${wechat.middle}")
	public void setUrlMiddle(String urlMiddle) {
		AwardDetailController.urlMiddle = urlMiddle;
	}

	@Value("${wechat.suffix}")
	public void setUrlSuffix(String urlSuffix) {
		AwardDetailController.urlSuffix = urlSuffix;
	}

	@Value("${wechat.exchange.url}")
	public void setProductmapUrl(String exchangeUrl) {
		AwardDetailController.exchangeUrl = exchangeUrl;
	}

	@RequestMapping(value = "/generateqrcode4awarddetail", method = RequestMethod.GET)
	@ResponseBody
	private void generateQRCode4Product(HttpServletRequest request, HttpServletResponse response) {
		/// 获取前端传递过来的商品Id
		long awardId = HttpServletRequestUtil.getLong(request, "awardId");
		// 从session里获取当前顾客的信息
		PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
		if (awardId != -1 && user != null && user.getUserId() != null) {
			// 获取当前时间戳，以保证二维码的时间有效性，精确到毫秒
			long timpStamp = System.currentTimeMillis();
			// 将店铺id和timestamp传入content，赋值到state中，这样微信获取到这些信息后会回传到授权信息的添加方法里
			// 加上aaa是为了一会的在添加信息的方法里替换这些信息使用
			String content = "{aaaawardIdaaa:" + awardId + ",aaacustomerIdaaa:" + user.getUserId()
					+ ",aaacreateTimeaaa:" + timpStamp + "}";
			try {
				// 将content的信息先进行base64编码以避免特殊字符造成的干扰，之后拼接目标URL
				String longUrl = urlPrefix + exchangeUrl + urlMiddle + URLEncoder.encode(content, "UTF-8") + urlSuffix;
				// 将目标URL转换成短的URL
				String shortUrl = ShortNetAddressUtil.getShortURL(longUrl);
				// 调用二维码生成的工具类方法，传入短的URL，生成二维码
				BitMatrix qRcodeImg = CodeUtil.generateQRCodeStream(shortUrl, response);
				// 将二维码以图片流的形式输出到前端
				MatrixToImageWriter.writeToStream(qRcodeImg, "png", response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
