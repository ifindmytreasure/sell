package com.zd.sell.utils;

import java.util.Random;

/**
 * Created by Unruly Wind on 2019/4/6/006.
 *
 * @author BlueMelancholy
 * @desc:
 */
public class KeyUtil {
	public static synchronized String generateUniqueKey(){
		Random random = new Random();
		Integer number = random.nextInt(900000) + 100000;
		return System.currentTimeMillis() + String.valueOf(number);
	}
}
