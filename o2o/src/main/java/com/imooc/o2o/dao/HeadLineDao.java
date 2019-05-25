package com.imooc.o2o.dao;

import com.imooc.o2o.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Unruly Wind on 2019/1/29/029.
 *
 * @author BlueMelancholy
 * @desc:
 */
public interface HeadLineDao {
	/**
	 * 根据传入的查询条件查询头条信息（头条名）
	 *
	 * @param headLineCondition
	 * @return
	 */
	List<HeadLine> queryHeadLine(@Param("headLineCondition") HeadLine headLineCondition);
}
