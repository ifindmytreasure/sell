package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.ProductDao;
import com.imooc.o2o.dao.ProductImgDao;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductImg;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.exceptions.ProductOperateException;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PageCalculator;
import com.imooc.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Unruly Wind on 2019/1/22/022.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Service
@SuppressWarnings("SpringJavaAutowiringInspection")
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;

	/**
	 * 1.处理缩略图，获取缩略图相对路径并赋值给product
	 * 2.往tb_product写入商品信息，获取productId
	 * 3.结合productId批量处理商品详情图
	 * 4.将商品详情图列表批量插入tb_product_img中
	 *
	 * @param product
	 * @param thumbnail
	 * @param productImgHolderList
	 * @return
	 * @throws ProductOperateException
	 */
	@Override
	@Transactional
	public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList) throws ProductOperateException {
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			//设置首次添加时间，修改时间
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			//默认为上架状态
			product.setEnableStatus(1);
			//若商品缩略图不为空则添加
			if (thumbnail != null) {
				addThumbnail(product, thumbnail);
			}
			try {
				//添加商品信息
				int effectedNum = productDao.insertProduct(product);
				if (effectedNum <= 0) {
					throw new ProductOperateException("创建商品失败");
				}
			} catch (Exception e) {
				throw new ProductOperateException("创建商品出现异常:" + e.toString());
			}
			//若商品详情图不为空则添加
			if (productImgHolderList != null && productImgHolderList.size() > 0) {
				addProductImgList(product, productImgHolderList);
			}
			return new ProductExecution(ProductStateEnum.SUCCESS, product);
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}

	}

	/**
	 * 通过productId获取唯一的商品信息
	 *
	 * @param productId
	 * @return
	 */
	@Override
	public Product getProductById(long productId) {
		return productDao.queryProductById(productId);
	}

	/**
	 * 根据productCondition分页返回商品列表
	 *
	 * @param productCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@Override
	public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);
		//查询商品总数
		int count = productDao.queryProductCount(productCondition);
		ProductExecution productExecution = new ProductExecution();
		if (productList != null) {
			productExecution.setCount(count);
			productExecution.setProductList(productList);
		} else {
			productExecution.setState(ProductStateEnum.INNER_ERROR.getState());
		}
		return productExecution;
	}

	/**
	 * 修改商品，图片处理（若修改则删除原有的旧图，添加上新图）
	 * 1.若缩略图参数有值，则处理缩略图，
	 * 若原先存在缩略图则先删除再添加新图，之后获取缩略图相对路径并赋值给product
	 * 2.若商品详情图列表参数有值，对商品详情图片列表进行同样的操作
	 * 3.将tb_product_img下面的该商品原先的商品详情图记录全部清除
	 * 4.更新tb_product_img以及tb_product的信息
	 *
	 * @param product
	 * @param thumbnail
	 * @param productImg
	 * @return
	 * @throws ProductOperateException
	 */
	@Override
	@Transactional
	public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImg) throws ProductOperateException {
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			// 给商品设置上默认属性
			product.setLastEditTime(new Date());
			// 若商品缩略图不为空且原有缩略图不为空则删除原有缩略图并添加
			if (thumbnail != null) {
				Product tempProduct = productDao.queryProductById(product.getProductId());
				if (tempProduct.getImgAddr() != null) {
					ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
				}
				addThumbnail(product, thumbnail);
			}
			// 如果有新存入的商品详情图，则将原先的删除，并添加新的图片
			if (productImg != null && productImg.size() > 0) {
				deleteProductImgList(product.getProductId());
				addProductImgList(product, productImg);
			}
			try {
				// 更新商品信息
				int effectedNum = productDao.updateProduct(product);
				if (effectedNum <= 0) {
					throw new ProductOperateException("更新商品信息失败");
				}
				return new ProductExecution(ProductStateEnum.SUCCESS, product);
			} catch (Exception e) {
				throw new ProductOperateException("更新商品信息失败:" + e.toString());
			}
		}
		return new ProductExecution(ProductStateEnum.EMPTY);
	}

	/**
	 * 添加缩略图
	 *
	 * @param product
	 * @param thumbnail
	 */
	private void addThumbnail(Product product, ImageHolder thumbnail) {
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		product.setImgAddr(thumbnailAddr);
	}

	/**
	 * 批量添加商品详情图
	 *
	 * @param product
	 * @param productImgHolderList
	 */
	private void addProductImgList(Product product, List<ImageHolder> productImgHolderList) {
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		System.out.println(product.getShop().getShopId());
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		//遍历图片一次去处理，并添加进productImg实体类里
		for (ImageHolder productImgHolder : productImgHolderList) {
			String imgAddr = ImageUtil.generateNormalThumbnail(productImgHolder, dest);
			ProductImg productImg = new ProductImg();
			productImg.setImgAddr(imgAddr);
			productImg.setProductId(product.getProductId());
			productImg.setCreateTime(new Date());
			productImgList.add(productImg);
		}
		//如果有图片需要添加的，就执行批量添加操作
		if (productImgList.size() > 0) {
			try {
				int effectedNum = productImgDao.batchInsertProductImg(productImgList);
				if (effectedNum < 0) {
					throw new ProductOperateException("添加商品详情图失败");
				}
			} catch (Exception e) {
				throw new ProductOperateException("添加商品详情图异常" + e.toString());
			}
		}
	}

	private void deleteProductImgList(long productId) {
		List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
		for (ProductImg productImg : productImgList) {
			ImageUtil.deleteFileOrPath(productImg.getImgAddr());
		}
		productImgDao.deleteProductImgByProductId(productId);
	}
}
