package com.queencastle.dao.mapper.shop;

import org.apache.ibatis.annotations.Param;

import com.queencastle.dao.model.shop.OrderItem;

public interface OrderItemMapper {
	OrderItem getById(@Param("id") String id);

	int insert(OrderItem orderItem);
}
