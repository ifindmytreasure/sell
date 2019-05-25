package com.imooc.o2o.service;

import com.imooc.o2o.entity.PersonInfo;

/**
 * Created by Unruly Wind on 2019/2/13/013.
 *
 * @author BlueMelancholy
 * @desc:
 */
public interface PersonInfoService {
	/**
	 * 根据用户Id获取personInfo信息
	 *
	 * @param userId
	 * @return
	 */
	PersonInfo getPersonInfoById(Long userId);
}
