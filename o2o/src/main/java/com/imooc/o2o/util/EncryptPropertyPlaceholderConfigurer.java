package com.imooc.o2o.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * Created by Unruly Wind on 2019/2/14/014.
 *
 * @author BlueMelancholy
 * @desc:
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	// 需要加密的字段数组
	private String[] encryptPropertyNames = {"jdbc.username", "jdbc.password"};

	/**
	 * 对关键的属性进行转换
	 */
	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		if (isEncryptProp(propertyName)) {
			// 对已加密的字段进行解密工作
			String decryptValue = DESUtil.getDecryptString(propertyValue);
			return decryptValue;
		} else {
			return propertyValue;
		}
	}

	private boolean isEncryptProp(String propertyName) {
		for (String encryptPropertyName : encryptPropertyNames) {
			if (encryptPropertyName.equals(propertyName)) {
				return true;
			}
		}
		return false;
	}
}
