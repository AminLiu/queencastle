package com.queencastle.dao.mapper.goods;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import com.queencastle.dao.model.goods.Category;

public interface CategoryMapper {
    int insert(Category category);

    Category getById(@Param("id") String id);

    List<Category> getAllCategories();

    Integer getCategorysCountByParams(@Param("map") Map<String, Object> map);

    List<Category> getCategorysByParams(@Param("page") Pageable pageable,
            @Param("map") Map<String, Object> map);
}
