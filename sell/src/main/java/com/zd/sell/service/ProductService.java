package com.zd.sell.service;

import com.zd.sell.dto.CartDTO;
import com.zd.sell.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Created by Unruly Wind on 2019/4/5/005.
 *
 * @author BlueMelancholy
 * @desc:
 */
public interface ProductService {
	Optional<ProductInfo> findOne(String productId);

	/**
	 * 查询所有在架商品列表
	 * @return
	 */
	List<ProductInfo> findUpAll();

	Page<ProductInfo> findAll(Pageable pageable);

	ProductInfo save(ProductInfo productInfo);

	/**
	 * 加库存
	 * @param cartDTOList
	 */
	void increaseStock(List<CartDTO> cartDTOList);

	/**
	 * 减库存
	 * @param cartDTOList
	 */
    void decreaseStock(List<CartDTO> cartDTOList);
	//上架

	//下架
}
