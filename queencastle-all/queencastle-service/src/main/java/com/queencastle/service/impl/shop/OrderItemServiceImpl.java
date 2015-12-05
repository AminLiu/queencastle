package com.queencastle.service.impl.shop;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.queencastle.dao.mapper.shop.OrderItemMapper;
import com.queencastle.dao.model.shop.OrderItem;
import com.queencastle.service.interf.shop.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private OrderItemMapper orderItemMapper;

	@Override
	public OrderItem getById(String id) {
		if (StringUtils.isNoneBlank(id)) {
			return orderItemMapper.getById(id);
		}
		return null;
	}

	@Override
	public int insert(OrderItem orderItem) {
		return orderItemMapper.insert(orderItem);
	}

}
