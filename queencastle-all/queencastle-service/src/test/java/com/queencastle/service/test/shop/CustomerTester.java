package com.queencastle.service.test.shop;

import org.junit.Test;

import com.queencastle.dao.model.shop.Customer;
import com.queencastle.dao.mybatis.IdTypeHandler;
import com.queencastle.service.test.BaseTest;

public class CustomerTester extends BaseTest {

	@Test
	// @Ignore
	public void getByIdTest() {
		String id = IdTypeHandler.encode(1l);
		Customer result = customerService.getById(id);
		if (result != null) {
			System.out.println("=========" + result.getId());
			System.out.println("=========" + result.getCustomerName());
			System.out.println("=========" + result.getCustomerSex());
			System.out.println("=========" + result.getCustomerTel());
		} else {
			System.out.println("=========");
		}
	}

	@Test
	// @Ignore
	public void CustomertTest() {

		Customer customer = new Customer();
		customer.setCustomerName("drgergeg");
		customer.setCustomerSex("男");
		customer.setCustomerTel("14567438796");
		customerService.insert(customer);
	}

}
