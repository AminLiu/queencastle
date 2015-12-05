package com.queencastle.dao.mapper.shop;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import com.queencastle.dao.model.shop.ShopProduct;

public interface ShopProductMapper {

	int insert(ShopProduct shopProduck);

	ShopProduct getById(@Param("id") String id);

	List<ShopProduct> getShopProductByParams(@Param("page") Pageable pageable, @Param("map") Map<String, Object> map);

	Integer getShopProductCountByParams(@Param("map") Map<String, Object> map);

}
