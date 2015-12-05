package com.queencastle.service.impl.shop;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.queencastle.dao.mapper.shop.ShopBrandMapper;
import com.queencastle.dao.model.shop.ShopBrand;
import com.queencastle.service.interf.shop.ShopBrandService;

@Service
public class ShopBrandServiceImpl implements ShopBrandService {

	@Autowired
	private ShopBrandMapper shopBrandMapper;

	@Override
	public ShopBrand getById(String id) {
		if (StringUtils.isNoneBlank(id)) {

			return shopBrandMapper.getById(id);
		}
		return null;
	}

	@Override
	public int insert(ShopBrand shopBrand) {
		return shopBrandMapper.insert(shopBrand);
	}

}
