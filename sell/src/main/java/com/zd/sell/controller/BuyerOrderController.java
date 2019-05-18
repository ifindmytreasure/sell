package com.zd.sell.controller;

import com.zd.sell.VO.ResultVO;
import com.zd.sell.converter.OrderFormToOrderDTOConverter;
import com.zd.sell.dto.OrderDTO;
import com.zd.sell.enums.ResultEnum;
import com.zd.sell.exception.SellException;
import com.zd.sell.form.OrderForm;
import com.zd.sell.service.BuyerService;
import com.zd.sell.service.OrderService;
import com.zd.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Unruly Wind on 2019/4/7/007.
 *
 * @author BlueMelancholy
 * @desc:
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private BuyerService buyerService;
	//创建订单
	@PostMapping("/create")
	public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
												BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			log.error("【创建订单】参数不正确, orderForm={}", orderForm);
			throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
					bindingResult.getFieldError().getDefaultMessage());
		}
		OrderDTO orderDTO = OrderFormToOrderDTOConverter.convert(orderForm);
		if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
			log.error("【创建订单】购物车不能为空");
			throw new SellException(ResultEnum.CART_EMPTY);
		}
		OrderDTO createResult = orderService.create(orderDTO);
		Map<String, String> map = new HashMap<>();
		map.put("orderId", createResult.getOrderId());
		return ResultVOUtil.success(map);
	}
	//订单列表
	@GetMapping("/list")
	public ResultVO<List<OrderDTO>> list(@RequestParam("openId") String openid,
										 @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
		if (StringUtils.isEmpty(openid)) {
			log.error("【查询订单列表】openid为空");
			throw new SellException(ResultEnum.PARAM_ERROR);
		}
		PageRequest request = PageRequest.of(page,size);
		Page<OrderDTO> orderDTOPage = orderService.findList(openid, request);

		return ResultVOUtil.success(orderDTOPage.getContent());
	}
	//订单详情
	@GetMapping("/detail")
	public ResultVO<OrderDTO> detail(@RequestParam("openId") String openid,
									 @RequestParam("orderId") String orderId) {
		OrderDTO orderDTO = buyerService.findOrderOne(openid,orderId);
		return ResultVOUtil.success(orderDTO);
	}

	//取消订单
	@PostMapping("/cancel")
	public ResultVO cancel(@RequestParam("openId") String openid,
						   @RequestParam("orderId") String orderId) {
		OrderDTO orderDTO = buyerService.findOrderOne(openid,orderId);
		orderService.cancel(orderDTO);
		return ResultVOUtil.success();
	}
}
