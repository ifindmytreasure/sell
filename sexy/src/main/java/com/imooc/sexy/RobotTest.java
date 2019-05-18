package com.imooc.sexy;

import com.imooc.sexy.domain.NullUser;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Unruly Wind on 2019/4/25/025.
 *
 * @author BlueMelancholy
 * @desc:
 */
public class RobotTest {
	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
		Class robotClass = Class.forName("com.imooc.sexy.Robot");
		Robot robot = (Robot) robotClass.newInstance();
		System.out.println("Class name is " + robotClass.getName());
		Method tag = robotClass.getDeclaredMethod("tag", String.class);
		tag.setAccessible(true);
		Object bob = tag.invoke(robot, "Bob");
		System.out.println(bob);
		Method sayHello = robotClass.getMethod("sayHello", String.class);
		System.out.println(sayHello.invoke(robot,"zhangsan"));
		Field name = robotClass.getDeclaredField("name");
		name.setAccessible(true);
		name.set(robot,"lisi");
		Object bobs = tag.invoke(robot, "Bob");
		System.out.println(bobs);
	}
}
