package com.zd.sell.repository;

import com.zd.sell.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Unruly Wind on 2019/4/5/005.
 *
 * @author BlueMelancholy
 * @desc:
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
	List<OrderDetail> findByOrderId(String orderId);
}
