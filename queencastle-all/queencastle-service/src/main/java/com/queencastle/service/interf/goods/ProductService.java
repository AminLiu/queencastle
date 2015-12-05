package com.queencastle.service.interf.goods;

import java.util.List;
import java.util.Map;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.goods.Product;

public interface ProductService {

    int insert(Product product);

    Product getById(String id);

    List<Product> getByCategoryId(String categoryId);

    List<Product> getAllProducts();

    PageInfo<Product> getProductsByParams(int page, int rows, Map<String, Object> map);
}
