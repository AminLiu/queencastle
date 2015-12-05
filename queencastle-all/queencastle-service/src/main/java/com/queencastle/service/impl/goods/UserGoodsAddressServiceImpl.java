package com.queencastle.service.impl.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.queencastle.dao.mapper.goods.UserGoodsAddressMapper;
import com.queencastle.dao.model.goods.UserGoodsAddress;
import com.queencastle.service.interf.goods.userGoodsAddressService;

@Service
public class UserGoodsAddressServiceImpl implements userGoodsAddressService {
	@Autowired
	private UserGoodsAddressMapper userGoodsAddressMapper;

	@Override
	public int insert(UserGoodsAddress userGoodsAddress) {
		return userGoodsAddressMapper.insert(userGoodsAddress);
	}

}
