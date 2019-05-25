package com.imooc.o2o.service;

import com.imooc.o2o.entity.Area;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Unruly Wind on 2018/12/23/023.
 *
 * @author BlueMelancholy
 * @desc:区域服务接口
 */
public interface AreaService {
	/**
	 * 定义保存好的key
	 */
	public static final String AREALISTKEY = "arealist";

	/**
	 * 获取区域列表,优先从缓存中获取
	 *
	 * @return
	 */
	List<Area> getAreaList();
}
