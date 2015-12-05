package com.queencastle.dao.mapper.shop;

import org.apache.ibatis.annotations.Param;

import com.queencastle.dao.model.shop.ShopItem;

public interface ShopItemMapper {
	ShopItem getById(@Param("id") String id);

	int insert(ShopItem shopItem);

}
