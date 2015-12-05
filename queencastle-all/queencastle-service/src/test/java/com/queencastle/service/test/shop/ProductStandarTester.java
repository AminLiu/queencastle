package com.queencastle.service.test.shop;

import org.junit.Ignore;
import org.junit.Test;

import com.queencastle.dao.model.shop.ProductStandard;
import com.queencastle.dao.mybatis.IdTypeHandler;
import com.queencastle.service.test.BaseTest;

public class ProductStandarTester extends BaseTest {

	@Test
	@Ignore
	public void getByIdTest() {
		String id = IdTypeHandler.encode(5l);
		ProductStandard result = productStandarService.getById(id);
		if (result != null) {
			System.out.println("=========" + result.getId());
			System.out.println("=========" + result.getColour());
			System.out.println("=========" + result.getImg());
			System.out.println("=========" + result.getPrice());
			System.out.println("=========" + result.getSize());
		} else {
			System.out.println("=========");
		}
	}

	@Test
	@Ignore
	public void ProductStandardTest() {

		ProductStandard productStandard = new ProductStandard();
		productStandard.setColour("wer");
		productStandard.setImg("dghd");
		productStandard.setSize("16");
		productStandard.setPrice(15);
		productStandarService.insert(productStandard);
	}
}
