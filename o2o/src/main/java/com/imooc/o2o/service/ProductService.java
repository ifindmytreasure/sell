package com.imooc.o2o.service;

import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.exceptions.ProductOperateException;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Unruly Wind on 2019/1/22/022.
 *
 * @author BlueMelancholy
 * @desc:
 */
public interface ProductService {
	/**
	 * 添加商品信息
	 *
	 * @param product
	 * @param thumbnail
	 * @param productImg
	 * @return
	 * @throws ProductOperateException
	 */
	ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImg) throws ProductOperateException;

	/**
	 * 通过productId获取唯一的商品信息
	 *
	 * @param productId
	 * @return
	 */
	Product getProductById(long productId);

	/**
	 * 根据productCondition分页返回商品列表
	 *
	 * @param productCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);

	/**
	 * 修改商品，图片处理（若修改则删除原有的旧图，添加上新图）
	 *
	 * @param product
	 * @param thumbnail
	 * @param productImg
	 * @return
	 * @throws ProductOperateException
	 */
	ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImg) throws ProductOperateException;
}
