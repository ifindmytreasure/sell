package com.zd.sell.service.impl;

import com.zd.sell.converter.OrderMasterToOrderDTOConverter;
import com.zd.sell.dto.CartDTO;
import com.zd.sell.dto.OrderDTO;
import com.zd.sell.entity.OrderDetail;
import com.zd.sell.entity.OrderMaster;
import com.zd.sell.entity.ProductInfo;
import com.zd.sell.enums.OrderStatusEnum;
import com.zd.sell.enums.PayStatusEnum;
import com.zd.sell.enums.ResultEnum;
import com.zd.sell.exception.SellException;
import com.zd.sell.repository.OrderDetailRepository;
import com.zd.sell.repository.OrderMasterRepository;
import com.zd.sell.service.OrderService;
import com.zd.sell.service.ProductService;
import com.zd.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Unruly Wind on 2019/4/6/006.
 *
 * @author BlueMelancholy
 * @desc:
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	@Autowired
	private OrderMasterRepository orderMasterRepository;
	/**
	 * 创建订单.
	 *
	 * @param orderDTO
	 */
	@Override
	@Transactional
	public OrderDTO create(OrderDTO orderDTO) {
		String orderId = KeyUtil.generateUniqueKey();
		BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
		//1. 查询商品（数量, 价格）
		for (OrderDetail orderDetail : orderDTO.getOrderDetailList()){
			Optional<ProductInfo> productInfoOptional = productService.findOne(orderDetail.getProductId());
			ProductInfo productInfo = productInfoOptional.get();
			if (productInfo == null){
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			/**
			 * 2.计算处理某件商品的总价并在遍历的时候叠加
			 */
			orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
			orderDetail.setOrderId(orderId);
			orderDetail.setDetailId(KeyUtil.generateUniqueKey());
			BeanUtils.copyProperties(productInfo,orderDetail);
			orderDetailRepository.save(orderDetail);
		}
		//3. 写入订单数据库（orderMaster和orderDetail）
		OrderMaster orderMaster = new OrderMaster();
		orderDTO.setOrderId(orderId);
		BeanUtils.copyProperties(orderDTO, orderMaster);
		orderMaster.setOrderAmount(orderAmount);
		orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
		orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
		orderMasterRepository.save(orderMaster);

		//扣库存
		List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e -> new CartDTO(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
		productService.decreaseStock(cartDTOList);
		return orderDTO;
	}

	/**
	 * 查询单个订单.
	 *
	 * @param orderId
	 */
	@Override
	public OrderDTO findOne(String orderId) {
		Optional<OrderMaster> orderMasterOptional = orderMasterRepository.findById(orderId);
		OrderMaster orderMaster = orderMasterOptional.get();
		if (orderMaster == null){
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}
		List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
		if (CollectionUtils.isEmpty(orderDetailList)) {
			throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
		}
		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(orderMaster,orderDTO);
		orderDTO.setOrderDetailList(orderDetailList);
		return orderDTO;
	}

	/**
	 * 查询订单列表.
	 *
	 * @param buyerOpenid
	 * @param pageable
	 */
	@Override
	public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
		Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid,pageable);
		List<OrderDTO> orderDTOList = OrderMasterToOrderDTOConverter.convert(orderMasterPage.getContent());
		Page<OrderDTO> orderDTOPage = new PageImpl<>(orderDTOList,pageable,orderMasterPage.getTotalElements());
		return orderDTOPage;
	}

	/**
	 * 取消订单.
	 *
	 * @param orderDTO
	 */
	@Override
	@Transactional
	public OrderDTO cancel(OrderDTO orderDTO) {
		OrderMaster orderMaster = new OrderMaster();
		//判断订单状态
		if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
			log.error("【取消订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}
		//修改订单状态
		orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
		BeanUtils.copyProperties(orderDTO,orderMaster);
		OrderMaster updateResult = orderMasterRepository.save(orderMaster);
		if (updateResult == null) {
			log.error("【取消订单】更新失败, orderMaster={}", orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}
		//返回库存
		if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
			log.error("【取消订单】订单中无商品详情, orderDTO={}", orderDTO);
			throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
		}
		List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e -> new CartDTO(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
		productService.increaseStock(cartDTOList);
		//如果已支付，需退款
		//todo
		return orderDTO;
	}

	/**
	 * 完结订单.
	 *
	 * @param orderDTO
	 */
	@Override
	public OrderDTO finish(OrderDTO orderDTO) {
		if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
			log.error("【完结订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}
		//修改订单状态
		orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderDTO, orderMaster);
		OrderMaster updateResult = orderMasterRepository.save(orderMaster);
		if (updateResult == null) {
			log.error("【完结订单】更新失败, orderMaster={}", orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}
		return orderDTO;
	}

	/**
	 * 支付订单.
	 *
	 * @param orderDTO
	 */
	@Override
	public OrderDTO paid(OrderDTO orderDTO) {
		//判断订单状态
		if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
			log.error("【订单支付完成】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}
		//判断支付状态
		if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
			log.error("【订单支付完成】订单支付状态不正确, orderDTO={}", orderDTO);
			throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
		}
		//修改支付状态
		orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderDTO, orderMaster);
		OrderMaster updateResult = orderMasterRepository.save(orderMaster);
		if (updateResult == null) {
			log.error("【订单支付完成】更新失败, orderMaster={}", orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}
		return orderDTO;
	}
}
