package com.queencastle.dao.mapper.shop;

import org.apache.ibatis.annotations.Param;

import com.queencastle.dao.model.shop.ShopOrder;

public interface ShopOrderMapper {
	
	ShopOrder getById(@Param("id") String id);
}
