package com.queencastle.service.test.shop;

import org.junit.Ignore;
import org.junit.Test;

import com.queencastle.dao.model.shop.ShopItem;
import com.queencastle.dao.model.shop.ShopProduct;
import com.queencastle.dao.mybatis.IdTypeHandler;
import com.queencastle.service.test.BaseTest;

public class ShopItemTester extends BaseTest {

	@Test
	@Ignore
	public void getByIdTest() {
		String id = IdTypeHandler.encode(3l);
		ShopItem result = shopItemService.getById(id);
		if (result != null) {
			System.out.println("=========" + result.getShopProduct().getId());
			System.out.println("=========" + result.getAmount());
		} else {
			System.out.println("=========");
		}
	}

	@Test
	@Ignore
	public void ShopItemTest() {

		ShopItem shopItem = new ShopItem();
		ShopProduct shopProduct = shopProductService.getById(IdTypeHandler.encode(1));
		shopItem.setShopProduct(shopProduct);
		shopItem.setAmount(100);
		shopItemService.insert(shopItem);
	}

}
