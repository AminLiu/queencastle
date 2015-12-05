package com.queencastle.dao.mapper.shop;

import org.apache.ibatis.annotations.Param;

import com.queencastle.dao.model.shop.ShopBrand;

public interface ShopBrandMapper {

	ShopBrand getById(@Param("id") String id);

	int insert(ShopBrand shopBrand);

}
