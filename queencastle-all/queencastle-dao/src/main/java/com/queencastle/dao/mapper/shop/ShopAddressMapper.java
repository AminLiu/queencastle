package com.queencastle.dao.mapper.shop;

import org.apache.ibatis.annotations.Param;

import com.queencastle.dao.model.shop.ShopAddress;

public interface ShopAddressMapper {
	ShopAddress getById(@Param("id") String id);

	int insert(ShopAddress shopAddress);

}
