package com.imooc.sexy;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import java.io.*;

/**
 * Created by Unruly Wind on 2019/4/29/029.
 *
 * @author BlueMelancholy
 * @desc:
 */
public class MyClassLoader extends ClassLoader{
	private String path;
	private String classLoaderName;

	public MyClassLoader(String path, String classLoaderName) {
		this.path = path;
		this.classLoaderName = classLoaderName;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] bytes = loadClassData(name);
		return defineClass(name,bytes,0,bytes.length);
	}
	//加载类文件
	private byte[] loadClassData(String name) {
		name = path + name + ".class";
		InputStream in = null;
		ByteArrayOutputStream out = null;
		try {
			in = new FileInputStream(new File(name));
			out = new ByteArrayOutputStream();
			int i = 0;
			while ((i = in.read()) != -1){
				out.write(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return out.toByteArray();
	}
}
