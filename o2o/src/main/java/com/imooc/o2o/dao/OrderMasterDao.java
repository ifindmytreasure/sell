package com.imooc.o2o.dao;

import com.imooc.o2o.entity.OrderMaster;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Unruly Wind on 2019/5/6/006.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Repository
public interface OrderMasterDao {
	/**
	 * 插入订单
	 * @param orderMaster
	 * @return
	 */
	int insertOrderMaster(OrderMaster orderMaster);

	/**
	 * 根据订单号查询订单信息
	 * @param orderId
	 * @return
	 */
	OrderMaster queryOrderMasterByOrderId(Integer orderId);

	/**
	 * 根据买家
	 * @param buyerId
	 * @return
	 */
	List<Integer> queryOrderIdListByBuyerId(Integer buyerId);
}
