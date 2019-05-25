package com.imooc.o2o.util;

import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShortNetAddressUtil {
	public static String getShortURL(String longUrl) {
		try {
			String result = callHttp("http://suo.im/api.php?format=json&url=" + longUrl);
			JSONObject jsonResult = JSON.parseObject(result);
			return jsonResult.getString("url");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	private static String callHttp(String requestUrl) throws IOException {
		OkHttpClient httpClient = new OkHttpClient(); // 创建OkHttpClient对象
		Request request = new Request.Builder().url(requestUrl)// 请求接口。如果需要传参拼接到接口后面。
				.build();// 创建Request 对象
		Response response = null;
		response = httpClient.newCall(request).execute();// 得到Response 对象
		return response.body().string();
	}

	/**
	 * 百度短链接接口
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(getShortURL("https://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=sandbox/login"));
	}
}
