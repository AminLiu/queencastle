package com.queencastle.service.test.shop;

import org.junit.Ignore;
import org.junit.Test;

import com.queencastle.dao.model.shop.OrderItem;
import com.queencastle.dao.model.shop.ShopProduct;
import com.queencastle.dao.mybatis.IdTypeHandler;
import com.queencastle.service.test.BaseTest;

public class OrderItemTester extends BaseTest {

	@Test
	@Ignore
	public void getByIdTest() {
		String id = IdTypeHandler.encode(1l);
		OrderItem result = orderItemService.getById(id);
		if (result != null) {
			System.out.println("=========" + result.getShopProduct().getId());
			System.out.println("=========" + result.getAmount());
		} else {
			System.out.println("=========");
		}
	}

	@Test
	@Ignore
	public void ShopAddressTest() {

		OrderItem orderItem = new OrderItem();
		ShopProduct shopProduct = shopProductService.getById(IdTypeHandler.encode(1));
		orderItem.setShopProduct(shopProduct);
		orderItem.setAmount(100);
		orderItemService.insert(orderItem);
	}

}
