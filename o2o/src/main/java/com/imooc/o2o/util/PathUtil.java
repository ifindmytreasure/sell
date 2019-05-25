package com.imooc.o2o.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Unruly Wind on 2018/12/24/024.
 *
 * @author BlueMelancholy
 * @desc:获取图片路径和店铺图片路径的工具类
 */
@Configuration
public class PathUtil {
	private static String separator = System.getProperty("file.separator");
	private static String winPath;

	private static String linuxPath;

	private static String shopPath;

	private static String headLinePath;

	private static String shopCategoryPath;

	@Value("${win.base.path}")
	public void setWinPath(String winPath) {
		PathUtil.winPath = winPath;
	}

	@Value("${linux.base.path}")
	public void setLinuxPath(String linuxPath) {
		PathUtil.linuxPath = linuxPath;
	}

	@Value("${shop.relevant.path}")
	public void setShopPath(String shopPath) {
		PathUtil.shopPath = shopPath;
	}

	@Value("${headline.relevant.path}")
	public void setHeadLinePath(String headLinePath) {
		PathUtil.headLinePath = headLinePath;
	}

	@Value("${shopcategory.relevant.path}")
	public void setShopCategoryPath(String shopCategoryPath) {
		PathUtil.shopCategoryPath = shopCategoryPath;
	}

	public static String getImgBasePath() {
		String os = System.getProperty("os.name");
		String basePath = "";
		/**
		 * 根据不同系统获取存放路径
		 */
		if (os.toLowerCase().startsWith("win")) {
			basePath = winPath;
		} else {
			basePath = linuxPath;
		}
		basePath.replace("/", separator);
		return basePath;
	}

	public static String getShopImagePath(long shopId) {
		String imagePath = shopPath + shopId + separator;
		return imagePath.replace("/", separator);
	}
}
