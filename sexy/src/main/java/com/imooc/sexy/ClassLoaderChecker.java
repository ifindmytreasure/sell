package com.imooc.sexy;

/**
 * Created by Unruly Wind on 2019/4/29/029.
 *
 * @author BlueMelancholy
 * @desc:
 */
public class ClassLoaderChecker {
	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		MyClassLoader myClassLoader = new MyClassLoader("C:\\Users\\Administrator\\Desktop\\", "myClassLoader");
		Class<?> myClass = myClassLoader.loadClass("MyClass");
		System.out.println(myClass.getClassLoader());
		System.out.println(myClass.getClassLoader().getParent());
		System.out.println(myClass.getClassLoader().getParent().getParent());
		System.out.println(myClass.getClassLoader().getParent().getParent().getParent());
		myClass.newInstance();
	}
}
