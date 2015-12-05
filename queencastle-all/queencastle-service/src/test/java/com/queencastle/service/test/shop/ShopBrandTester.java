package com.queencastle.service.test.shop;

import org.junit.Ignore;
import org.junit.Test;

import com.queencastle.dao.model.shop.ShopBrand;
import com.queencastle.dao.mybatis.IdTypeHandler;
import com.queencastle.service.test.BaseTest;

public class ShopBrandTester extends BaseTest {

	@Test
	@Ignore
	public void testInsert() {
		ShopBrand brand = new ShopBrand();
		brand.setCname("阿迪达斯");
		brand.setImg("aaaaaaaa");
		shopBrandService.insert(brand);
	}

	@Test
	public void testGetById(){
		String id = IdTypeHandler.encode(5);
		ShopBrand brand = shopBrandService.getById(id);
		System.out.println(brand.getCname());
	}
}
