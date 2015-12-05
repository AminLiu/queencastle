package com.queencastle.service.interf.shop;

import com.queencastle.dao.model.shop.ProductStandard;

public interface ProductStandardService {
	ProductStandard getById(String id);

	int insert(ProductStandard productStandard);

}
