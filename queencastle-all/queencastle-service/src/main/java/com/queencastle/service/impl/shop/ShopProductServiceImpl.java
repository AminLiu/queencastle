package com.queencastle.service.impl.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.mapper.shop.ShopProductMapper;
import com.queencastle.dao.model.shop.ProductStandard;
import com.queencastle.dao.model.shop.ShopProduct;
import com.queencastle.service.interf.shop.ProductStandardService;
import com.queencastle.service.interf.shop.ShopProductService;

@Service
public class ShopProductServiceImpl implements ShopProductService {
    @Autowired
    private ShopProductMapper shopProductMapper;
    @Autowired
    private ProductStandardService productStandardService;

    @Override
    public int insert(ShopProduct shopProduck) {
        return shopProductMapper.insert(shopProduck);
    }

    @Override
    public ShopProduct getById(String id) {
        ShopProduct shopProduct = shopProductMapper.getById(id);
        if (shopProduct != null) {
            String standardIds = shopProduct.getStandardIds();
            List<ProductStandard> list = new ArrayList<ProductStandard>();
            if (StringUtils.isNoneBlank(standardIds)) {
                String[] array = StringUtils.split(standardIds, ",");
                for (String standardId : array) {
                    ProductStandard standard = productStandardService.getById(standardId);
                    if (standard != null) {
                        list.add(standard);
                    }
                }
            }
            shopProduct.setStandards(list);
            String images = shopProduct.getImages();
            List<String> imgs = new ArrayList<String>();
            if (StringUtils.isNoneBlank(images)) {
                String[] array = StringUtils.split(images, ",");
                for (String ele : array) {
                    if (StringUtils.isNoneBlank(ele)) {
                        imgs.add(ele);
                    }
                }

            }
            shopProduct.setImgs(imgs);;
        }
        return shopProduct;
    }

    @Override
    public PageInfo<ShopProduct> getShopProductByParams(int page, int rows, Map<String, Object> map) {
        PageInfo<ShopProduct> pageInfo = new PageInfo<ShopProduct>();
        pageInfo.setPage(page);
        Integer count = shopProductMapper.getShopProductCountByParams(map);
        if (count == null || count == 0) {
            pageInfo.setTotal(0);
            pageInfo.setRows(new ArrayList<ShopProduct>());
            return pageInfo;
        }
        pageInfo.setTotal(count);
        page = (page <= 1) ? 1 : page;
        Pageable pageable = new PageRequest(page - 1, rows);

        List<ShopProduct> list = shopProductMapper.getShopProductByParams(pageable, map);
        pageInfo.setRows(list);
        return pageInfo;
    }

}
