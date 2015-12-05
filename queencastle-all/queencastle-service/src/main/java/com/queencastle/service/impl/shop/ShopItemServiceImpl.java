package com.queencastle.service.impl.shop;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.queencastle.dao.mapper.shop.ShopItemMapper;
import com.queencastle.dao.model.shop.ShopItem;
import com.queencastle.service.interf.shop.ShopItemService;

@Service
public class ShopItemServiceImpl implements ShopItemService
{

	@Autowired
	private ShopItemMapper shopItemMapper;

	@Override
	public ShopItem getById(String id) {
		if (StringUtils.isNoneBlank(id)) {
			return shopItemMapper.getById(id);
		}
		return null;
	}

	@Override
	public int insert(ShopItem shopItem) {
		return shopItemMapper.insert(shopItem);
	}

}
