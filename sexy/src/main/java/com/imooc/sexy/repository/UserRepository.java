package com.imooc.sexy.repository;

import com.imooc.sexy.User;import com.imooc.sexy.domain.ComplexList;import org.apache.ibatis.annotations.Param;import java.util.Collection;import java.util.List;

/**
 * Created by Unruly Wind on 2019/5/11/011.
 *
 * @author BlueMelancholy
 * @desc:
 */

public interface UserRepository {
	int deleteByPrimaryKey(Integer userId);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer userId);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	List<User> findAllByUserName(@Param("userName") String userName);

	Integer countByUserIdIn(@Param("userIdCollection") Collection<Integer> userIdCollection);

	int findByComplexList(ComplexList complexList);
}