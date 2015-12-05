package com.queencastle.service.test.shop;

import org.junit.Ignore;
import org.junit.Test;

import com.queencastle.dao.model.area.City;
import com.queencastle.dao.model.area.Province;
import com.queencastle.dao.model.shop.ShopAddress;
import com.queencastle.dao.mybatis.IdTypeHandler;
import com.queencastle.service.test.BaseTest;

public class ShopAddressTester extends BaseTest {
	@Test
	@Ignore
	public void getByIdTest() {
		String id = IdTypeHandler.encode(1l);
		ShopAddress result = shopAddressService.getById(id);
		if (result != null) {
			System.out.println("=========" + result.getId());
			System.out.println("=========" + result.getProvince());
			System.out.println("=========" + result.getCity());
			System.out.println("=========" + result.getDetail());
			System.out.println("=========" + result.getUserId());
		} else {
			System.out.println("=========");
		}
	}

	@Test
	@Ignore
	public void ShopAddressTest() {

		ShopAddress shopAddress = new ShopAddress();
		Province province = areaInfoService.getProvinceById(IdTypeHandler.encode(2));
		City city = areaInfoService.getCityById(IdTypeHandler.encode(23));
		shopAddress.setProvince(province);
		shopAddress.setCity(city);
		shopAddress.setDetail("市中心");
		shopAddress.setUserId(IdTypeHandler.encode(2));
		shopAddressService.insert(shopAddress);
	}

}
