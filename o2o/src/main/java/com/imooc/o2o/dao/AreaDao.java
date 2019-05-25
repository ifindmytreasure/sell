package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Area;

import java.util.List;

/**
 * Created by Unruly Wind on 2018/12/23/023.
 *
 * @author BlueMelancholy
 * @desc:区域DAO
 */
public interface AreaDao {
	/**
	 * 列出区域列表
	 *
	 * @return
	 */
	List<Area> queryArea();

	Area queryAreaById(int id);
}
