package com.imooc.o2o.util;

/**
 * Created by Unruly Wind on 2019/1/3/003.
 *
 * @author BlueMelancholy
 * @desc:
 */
public class PageCalculator {
	public static int calculateRowIndex(int pageIndex, int pageSize) {
		return pageIndex > 0 ? (pageIndex - 1) * pageSize : 0;
	}
}
