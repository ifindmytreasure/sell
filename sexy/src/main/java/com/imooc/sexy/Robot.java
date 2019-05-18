package com.imooc.sexy;

/**
 * Created by Unruly Wind on 2019/4/25/025.
 *
 * @author BlueMelancholy
 * @desc:
 */
public class Robot {
	private String name;
	public String sayHello(String name){
		return "Hello" + name;
	}
	private String tag(String tag){
		return tag + " " + name;
	}
	static {
		System.out.println("Hello Robot");
	}
}
