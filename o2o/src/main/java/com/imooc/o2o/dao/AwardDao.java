package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Award;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Unruly Wind on 2019/3/3/003.
 *
 * @author BlueMelancholy
 * @desc:
 */
public interface AwardDao {
	/**
	 * 添加奖品
	 *
	 * @param award
	 * @return
	 */
	int insertAward(Award award);

	/**
	 * 根据奖品id和店铺id删除商品
	 *
	 * @param awardId
	 * @param shopId
	 * @return
	 */
	int deleteAward(@Param("awardId") long awardId, @Param("shopId") long shopId);

	/**
	 * 修改奖品信息
	 *
	 * @param award
	 * @return
	 */
	int updateAward(Award award);

	/**
	 * 分页查询奖品
	 *
	 * @param awardCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<Award> queryAwardList(@Param("awardCondition") Award awardCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

	/**
	 * 查询奖品数量
	 *
	 * @param awardCondition
	 * @return
	 */
	int queryAwardCount(@Param("awardCondition") Award awardCondition);

	/**
	 * 通过awardId查询奖品信息
	 *
	 * @param awardId
	 * @return
	 */
	Award queryAwardByAwardId(long awardId);
}
