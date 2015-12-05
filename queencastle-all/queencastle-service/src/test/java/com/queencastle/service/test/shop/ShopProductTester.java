package com.queencastle.service.test.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Ignore;
import org.junit.Test;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.PropertyDict;
import com.queencastle.dao.model.shop.ProductStandard;
import com.queencastle.dao.model.shop.ShopBrand;
import com.queencastle.dao.model.shop.ShopProduct;
import com.queencastle.dao.mybatis.IdTypeHandler;
import com.queencastle.service.test.BaseTest;

public class ShopProductTester extends BaseTest {

    @Test
    @Ignore
    public void insertTest() {

        ShopProduct shopProduct = new ShopProduct();
        PropertyDict category = new PropertyDict();
        category.setId(IdTypeHandler.encode(12));
        shopProduct.setCategory(category);


        ShopBrand brand = new ShopBrand();
        brand.setId(IdTypeHandler.encode(2334));
        shopProduct.setBrand(brand);


        shopProduct.setAmount(129);

        shopProduct.setImages("a,bc,asfsdf");

        List<ProductStandard> standards = new ArrayList<ProductStandard>();
        for (int i = 0; i < 5; i++) {
            String tmpId = IdTypeHandler.encode(RandomUtils.nextLong(1, 2000));
            ProductStandard standard = new ProductStandard();
            standard.setId(tmpId);
            standards.add(standard);
        }
        shopProduct.setStandards(standards);
        shopProductService.insert(shopProduct);

    }

    @Test
    // @Ignore
    public void getById() {
        String id = IdTypeHandler.encode(2L);
        ShopProduct result = shopProductService.getById(id);
        System.out.println("====" + result.getId());
        System.out.println("====" + result.getCategory().getId());
    }

    @Test
    @Ignore
    public void getParam() {

        Map<String, Object> map = new HashMap<>();
        PageInfo<ShopProduct> result = shopProductService.getShopProductByParams(1, 2, map);
        System.out.println("===========" + result.getPage());
        System.out.println("==========" + result.getTotal());
    }
}
