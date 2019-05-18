package com.zd.sell.repository;

import com.zd.sell.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Unruly Wind on 2019/4/4/004.
 *
 * @author BlueMelancholy
 * @desc:
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
	List<ProductInfo> findAllByAndProductStatus(Integer productStatus);
}
