package com.zd.sell.repository;

import com.zd.sell.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Unruly Wind on 2019/4/5/005.
 *
 * @author BlueMelancholy
 * @desc:
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
	Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
