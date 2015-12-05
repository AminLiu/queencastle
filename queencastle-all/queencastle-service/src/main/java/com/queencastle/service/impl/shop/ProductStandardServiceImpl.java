package com.queencastle.service.impl.shop;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.queencastle.dao.mapper.shop.ProductStandardMapper;
import com.queencastle.dao.model.shop.ProductStandard;
import com.queencastle.service.interf.shop.ProductStandardService;

@Service
public class ProductStandardServiceImpl implements ProductStandardService {
	@Autowired
	private ProductStandardMapper productStandardMapper;

	@Override
	public ProductStandard getById(String id) {
		if (StringUtils.isNoneBlank(id)) {

			return productStandardMapper.getById(id);
		}
		return null;
	}

	@Override
	public int insert(ProductStandard productStandard) {
		return productStandardMapper.insert(productStandard);
	}

}
