package com.queencastle.service.interf.goods;

import java.util.List;
import java.util.Map;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.goods.Category;

public interface CategoryService {

    int insert(Category category);

    Category getById(String id);

    List<Category> getAllCategories();

    PageInfo<Category> getCategorysByParams(int page, int rows, Map<String, Object> map);

}
