package com.queencastle.service.interf.shop;

import com.queencastle.dao.model.shop.OrderItem;

public interface OrderItemService {
	OrderItem getById(String id);

	int insert(OrderItem orderItem);

}
