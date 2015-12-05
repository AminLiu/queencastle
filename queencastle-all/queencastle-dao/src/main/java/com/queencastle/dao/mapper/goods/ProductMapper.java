package com.queencastle.dao.mapper.goods;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import com.queencastle.dao.model.goods.Product;

public interface ProductMapper {

    int insert(Product product);

    Product getById(@Param("id") String id);

    List<Product> getByCategoryId(@Param("categoryId") String categoryId);

    List<Product> getAllProducts();

    Integer getProductsCountByParams(@Param("map") Map<String, Object> map);

    List<Product> getProductsByParams(@Param("page") Pageable pageable,
            @Param("map") Map<String, Object> map);
}
