package com.zd.sell.converter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.zd.sell.dto.OrderDTO;
import com.zd.sell.entity.OrderDetail;
import com.zd.sell.enums.ResultEnum;
import com.zd.sell.exception.SellException;
import com.zd.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Unruly Wind on 2019/4/7/007.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Slf4j
public class OrderFormToOrderDTOConverter {
	public static OrderDTO convert(OrderForm orderForm){
		Gson gson = new Gson();
		OrderDTO orderDTO = new OrderDTO();

		orderDTO.setBuyerName(orderForm.getName());
		orderDTO.setBuyerPhone(orderForm.getPhone());
		orderDTO.setBuyerAddress(orderForm.getAddress());
		orderDTO.setBuyerOpenid(orderForm.getOpenid());
		List<OrderDetail> orderDetailList = new ArrayList<>();
		try {
			orderDetailList = gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
		} catch (JsonSyntaxException e) {
			log.error("【对象转换】错误, string={}", orderForm.getItems());
			throw new SellException(ResultEnum.PARAM_ERROR);
		}
		orderDTO.setOrderDetailList(orderDetailList);
		return orderDTO;
	}
}
