package com.queencastle.service.impl.shop;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.queencastle.dao.mapper.shop.ShopAddressMapper;
import com.queencastle.dao.model.shop.ShopAddress;
import com.queencastle.service.interf.shop.ShopAddressService;

@Service
public class ShopAddressServiceImpl implements ShopAddressService {

	@Autowired
	private ShopAddressMapper shopAddressMapper;

	@Override
	public ShopAddress getById(String id) {
		if (StringUtils.isNoneBlank(id)) {

			return shopAddressMapper.getById(id);
		}
		return null;
	}

	@Override
	public int insert(ShopAddress shopAddress) {
		return shopAddressMapper.insert(shopAddress);
	}

}
