package com.imooc.o2o.dto;

import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStateEnum;

import java.util.List;

/**
 * Created by Unruly Wind on 2018/12/25/025.
 *
 * @author BlueMelancholy
 * @desc:店铺的dto对象
 */
public class ShopExecution {
	private int state;
	private String stateInfo;
	private int count;
	//操作的店铺（增删改时用到）
	private Shop shop;
	//shop列表（店铺查询的时候所用）
	private List<Shop> shopList;

	//无参构造
	public ShopExecution() {
	}

	/**
	 * 店铺操作失败的时候使用的构造器
	 *
	 * @param shopStateEnum
	 */
	public ShopExecution(ShopStateEnum shopStateEnum) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	/**
	 * 店铺操作成功的时候使用的构造器
	 *
	 * @param shopStateEnum
	 * @param shop
	 */
	public ShopExecution(ShopStateEnum shopStateEnum, Shop shop) {
		this.state = shopStateEnum.getState();
		this.stateInfo = shopStateEnum.getStateInfo();
		this.shop = shop;
	}

	/**
	 * 店铺操作成功的时候使用的构造器
	 *
	 * @param stateEnum
	 * @param shopList
	 */
	public ShopExecution(ShopStateEnum stateEnum, List<Shop> shopList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shopList = shopList;
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

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public List<Shop> getShopList() {
		return shopList;
	}

	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}
}
