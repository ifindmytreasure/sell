package com.imooc.o2o.dto;

import com.imooc.o2o.entity.Product;
import com.imooc.o2o.enums.ProductStateEnum;

import java.util.List;

/**
 * Created by Unruly Wind on 2019/1/22/022.
 *
 * @author BlueMelancholy
 * @desc:
 */
public class ProductExecution {
	private int state;
	private String stateInfo;
	//商品数量
	private int count;
	private List<Product> productList;
	// 操作的product（增删改商品的时候用）
	private Product product;

	public ProductExecution() {
	}

	//失败的构造器
	public ProductExecution(ProductStateEnum productStateEnum) {
		this.state = productStateEnum.getState();
		this.stateInfo = productStateEnum.getStateInfo();
	}

	//成功的构造器
	public ProductExecution(ProductStateEnum productStateEnum, Product product) {
		this.state = productStateEnum.getState();
		this.stateInfo = productStateEnum.getStateInfo();
		this.product = product;
	}

	//成功的构造器
	public ProductExecution(ProductStateEnum productStateEnum, List<Product> productList) {
		this.state = productStateEnum.getState();
		this.stateInfo = productStateEnum.getStateInfo();
		this.productList = productList;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
