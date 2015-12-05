package com.queencastle.service.impl.goods;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.mapper.goods.CategoryMapper;
import com.queencastle.dao.model.goods.Category;
import com.queencastle.service.interf.goods.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public int insert(Category category) {
        return categoryMapper.insert(category);
    }

    @Override
    public Category getById(String id) {
        if (StringUtils.isNoneBlank(id)) {
            return categoryMapper.getById(id);
        }
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryMapper.getAllCategories();
    }

    @Override
    public PageInfo<Category> getCategorysByParams(int page, int rows, Map<String, Object> map) {
        PageInfo<Category> pageInfo = new PageInfo<Category>();
        pageInfo.setPage(page);
        Integer count = categoryMapper.getCategorysCountByParams(map);
        if (count == null || count == 0) {
            pageInfo.setTotal(0);
            pageInfo.setRows(new ArrayList<Category>());
            return pageInfo;
        }
        pageInfo.setTotal(count);
        page = (page <= 1) ? 1 : page;
        Pageable pageable = new PageRequest(page - 1, rows);

        List<Category> list = categoryMapper.getCategorysByParams(pageable, map);
        pageInfo.setRows(list);
        return pageInfo;
    }

}
