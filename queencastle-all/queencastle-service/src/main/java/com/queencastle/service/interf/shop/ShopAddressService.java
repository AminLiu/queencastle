package com.queencastle.service.interf.shop;

import com.queencastle.dao.model.shop.ShopAddress;

public interface ShopAddressService {
	ShopAddress getById(String id);

	int insert(ShopAddress shopAddress);

}
