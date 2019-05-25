package com.imooc.o2o.entity;

import com.imooc.o2o.enums.OrderStatusEnum;
import com.imooc.o2o.enums.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Unruly Wind on 2019/5/6/006.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Data
public class OrderMaster {
	/** 订单id. */
	private Integer orderId;

	/** 买家名字. */
	private String buyerName;

	/** 买家手机号. */
	private String buyerPhone;

	/** 买家地址. */
	private String buyerAddress;

	/** 买家id. */
	private Integer buyerId;

	/** 订单总金额. */
	private BigDecimal orderAmount;

	/** 订单状态, 默认为0新下单. */
	private Integer orderStatus = OrderStatusEnum.NEW.getState();

	/** 支付状态, 默认为0未支付. */
	private Integer payStatus = PayStatusEnum.WAIT.getState();

	/** 创建时间. */
	private Date createTime;

	/** 更新时间. */
	private Date updateTime;
}
