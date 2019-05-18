package com.imooc.sexy;

/**
 * Created by Unruly Wind on 2019/4/29/029.
 *
 * @author BlueMelancholy
 * @desc:
 */
public class RobotForNameAndClassLoader {
	public static void main(String[] args) throws ClassNotFoundException {
		ClassLoader classLoader = Robot.class.getClassLoader();
		Class<?> forName = Class.forName("com.imooc.sexy.Robot");

	}
}
