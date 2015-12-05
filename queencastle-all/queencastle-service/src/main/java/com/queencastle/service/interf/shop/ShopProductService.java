package com.queencastle.service.interf.shop;

import java.util.Map;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.shop.ShopProduct;

public interface ShopProductService {

	int insert(ShopProduct shopProduck);

	ShopProduct getById(String id);

	PageInfo<ShopProduct> getShopProductByParams(int page, int rows, Map<String, Object> map);

}
