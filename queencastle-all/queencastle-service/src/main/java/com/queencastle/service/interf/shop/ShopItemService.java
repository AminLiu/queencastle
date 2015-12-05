package com.queencastle.service.interf.shop;

import com.queencastle.dao.model.shop.ShopItem;

public interface ShopItemService {
	ShopItem getById(String id);

	int insert(ShopItem shopItem);

}
