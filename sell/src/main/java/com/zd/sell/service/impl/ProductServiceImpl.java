package com.zd.sell.service.impl;

import com.zd.sell.dto.CartDTO;
import com.zd.sell.entity.ProductInfo;
import com.zd.sell.enums.ProductStatusEnum;
import com.zd.sell.enums.ResultEnum;
import com.zd.sell.exception.SellException;
import com.zd.sell.repository.ProductInfoRepository;
import com.zd.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by Unruly Wind on 2019/4/5/005.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductInfoRepository repository;
	@Override
	public Optional<ProductInfo> findOne(String productId) {
		return repository.findById(productId);
	}

	/**
	 * 查询所有在架商品列表
	 *
	 * @return
	 */
	@Override
	public List<ProductInfo> findUpAll() {
		return repository.findAllByAndProductStatus(ProductStatusEnum.UP.getCode());
	}

	@Override
	public Page<ProductInfo> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public ProductInfo save(ProductInfo productInfo) {
		return repository.save(productInfo);
	}

	/**
	 * 加库存
	 *
	 * @param cartDTOList
	 */
	@Override
	@Transactional
	public void increaseStock(List<CartDTO> cartDTOList) {
		for (CartDTO cartDTO : cartDTOList){
			Optional<ProductInfo> productInfoOptional = repository.findById(cartDTO.getProductId());
			ProductInfo productInfo = productInfoOptional.get();
			if (productInfo == null){
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
			productInfo.setProductStock(result);
			repository.save(productInfo);
		}
	}

	/**
	 * 减库存
	 *
	 * @param cartDTOList
	 */
	@Override
	@Transactional
	public void decreaseStock(List<CartDTO> cartDTOList) {
		for (CartDTO cartDTO : cartDTOList){
			Optional<ProductInfo> productInfoOptional = repository.findById(cartDTO.getProductId());
			ProductInfo productInfo = productInfoOptional.get();
			if (productInfo == null){
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
			if (result < 0){
				throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
			}
			productInfo.setProductStock(result);
			repository.save(productInfo);
		}
	}
}
