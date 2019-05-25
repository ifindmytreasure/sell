package com.imooc.o2o.service;

import com.imooc.o2o.entity.Area;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Unruly Wind on 2018/12/23/023.
 *
 * @author BlueMelancholy
 * @desc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaServiceTest {
	@Autowired
	private AreaService areaService;
	@Autowired
	private CacheService cacheService;

	@Test
	public void testGetAreaList() {
		List<Area> areaList = areaService.getAreaList();
		System.out.println(areaList);
		cacheService.removeFromCache(areaService.AREALISTKEY);
		areaList = areaService.getAreaList();
	}
}
