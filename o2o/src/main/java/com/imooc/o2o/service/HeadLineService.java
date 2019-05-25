package com.imooc.o2o.service;

import com.imooc.o2o.entity.HeadLine;

import java.io.IOException;
import java.util.List;

/**
 * Created by Unruly Wind on 2019/1/29/029.
 *
 * @author BlueMelancholy
 * @desc:
 */
public interface HeadLineService {
	public static final String HLLISTKEY = "headlinelist";

	/**
	 * 根据传入的查询条件查询头条信息（头条名），优先从缓存中获取
	 *
	 * @param headLineCondition
	 * @return
	 * @throws IOException
	 */
	List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException;
}
