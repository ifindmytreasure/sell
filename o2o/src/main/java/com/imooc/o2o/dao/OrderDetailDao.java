package com.imooc.o2o.dao;

import com.imooc.o2o.entity.OrderDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Unruly Wind on 2019/5/6/006.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Repository
public interface OrderDetailDao {
	/**
	 * 插入订单详情
	 * @param orderDetail
	 * @return
	 */
	int insertOrderDetail(OrderDetail orderDetail);

	/**
	 * 查询订单详情
	 * @param orderDetailCondition
	 * @return
	 */
	List<OrderDetail> queryOrderDetailList(@Param("orderDetailCondition") OrderDetail orderDetailCondition);

	/**
	 * 根据订单号查询订单详情
	 * @param orderIdList
	 * @return
	 */
	List<OrderDetail> queryOrderDetailListByOrderIds(List<Integer> orderIdList);
}
