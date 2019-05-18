package com.zd.sell.converter;

import com.zd.sell.dto.OrderDTO;
import com.zd.sell.entity.OrderMaster;
import org.hibernate.criterion.Order;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Unruly Wind on 2019/4/7/007.
 *
 * @author BlueMelancholy
 * @desc:
 */
public class OrderMasterToOrderDTOConverter {
	public static OrderDTO convert(OrderMaster orderMaster){
		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(orderMaster,orderDTO);
		return orderDTO;
	}
	public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
		List<OrderDTO> orderDTOList = orderMasterList.stream().map(e -> convert(e)).collect(Collectors.toList());
		return orderDTOList;
	}
}
