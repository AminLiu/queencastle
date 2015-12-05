package com.queencastle.service.interf.shop;

import java.util.Map;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.shop.Customer;

public interface CustomerService {
	Customer getById(String id);

	int insert(Customer customer);

	PageInfo<Customer> getCustomerByParams(int page, int rows, Map<String, Object> map);
}
