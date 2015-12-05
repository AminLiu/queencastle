package com.queencastle.dao.mapper.shop;

import org.apache.ibatis.annotations.Param;

import com.queencastle.dao.model.shop.ProductStandard;

public interface ProductStandardMapper {

	ProductStandard getById(@Param("id") String id);

	int insert(ProductStandard productStandard);
}
