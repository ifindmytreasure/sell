package com.zd.sell.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zd.sell.entity.OrderDetail;
import com.zd.sell.enums.OrderStatusEnum;
import com.zd.sell.enums.PayStatusEnum;
import com.zd.sell.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Unruly Wind on 2019/4/6/006.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Data
//见application.yml
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
	private String orderId;

	/** 买家名字. */
	private String buyerName;

	/** 买家手机号. */
	private String buyerPhone;

	/** 买家地址. */
	private String buyerAddress;

	/** 买家微信Openid. */
	private String buyerOpenid;

	/** 订单总金额. */
	private BigDecimal orderAmount;

	/** 订单状态, 默认为0新下单. */
	private Integer orderStatus = OrderStatusEnum.NEW.getCode();

	/** 支付状态, 默认为0未支付. */
	private Integer payStatus = PayStatusEnum.WAIT.getCode();

	/** 创建时间. */
	@JsonSerialize(using = Date2LongSerializer.class)
	private Date createTime;

	/** 更新时间. */
	@JsonSerialize(using = Date2LongSerializer.class)
	private Date updateTime;

	/**
	 * 订单详情
	 */
	private List<OrderDetail> orderDetailList;
}
