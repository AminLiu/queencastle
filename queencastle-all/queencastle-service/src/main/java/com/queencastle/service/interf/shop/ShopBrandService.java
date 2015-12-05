package com.queencastle.service.interf.shop;

import com.queencastle.dao.model.shop.ShopBrand;

public interface ShopBrandService {

	ShopBrand getById(String id);

	int insert(ShopBrand shopBrand);

}
