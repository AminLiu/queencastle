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
import com.queencastle.dao.mapper.goods.ProductMapper;
import com.queencastle.dao.model.goods.Product;
import com.queencastle.dao.utils.jedis.ObjectJedisCache;
import com.queencastle.service.interf.goods.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ObjectJedisCache productCache;

    @Override
    public int insert(Product product) {
        return productMapper.insert(product);
    }

    @Override
    public Product getById(String id) {
        if (StringUtils.isNoneBlank(id)) {
            Product product = (Product) productCache.getObj(id);
            if (product == null) {
                product = productMapper.getById(id);
                if (product != null) {
                    productCache.setObj(id, product);
                }
            }
            return product;
        }
        return null;
    }

    @Override
    public List<Product> getByCategoryId(String categoryId) {
        if (StringUtils.isNoneBlank(categoryId)) {
            return productMapper.getByCategoryId(categoryId);
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return productMapper.getAllProducts();
    }

    @Override
    public PageInfo<Product> getProductsByParams(int page, int rows, Map<String, Object> map) {
        PageInfo<Product> pageInfo = new PageInfo<Product>();
        pageInfo.setPage(page);
        Integer count = productMapper.getProductsCountByParams(map);
        if (count == null || count == 0) {
            pageInfo.setTotal(0);
            pageInfo.setRows(new ArrayList<Product>());
            return pageInfo;
        }
        pageInfo.setTotal(count);
        page = (page <= 1) ? 1 : page;
        Pageable pageable = new PageRequest(page - 1, rows);

        List<Product> list = productMapper.getProductsByParams(pageable, map);
        pageInfo.setRows(list);
        return pageInfo;
    }

}
