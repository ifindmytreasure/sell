package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.PersonInfoDao;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Unruly Wind on 2019/2/13/013.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Service
@SuppressWarnings("SpringJavaAutowiringInspection")
public class PersonInfoServiceImpl implements PersonInfoService {
	@Autowired
	private PersonInfoDao personInfoDao;

	/**
	 * 根据用户Id获取personInfo信息
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public PersonInfo getPersonInfoById(Long userId) {
		return personInfoDao.queryPersonInfoById(userId);
	}
}
