package com.queencastle.service.impl.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.mapper.shop.CustomerMapper;
import com.queencastle.dao.model.shop.Customer;
import com.queencastle.service.interf.shop.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public Customer getById(String id) {
		if (StringUtils.isNoneBlank(id)) {

			return customerMapper.getById(id);
		}
		return null;
	}

	@Override
	public int insert(Customer customer) {
		return customerMapper.insert(customer);
	}

	@Override
	public PageInfo<Customer> getCustomerByParams(int page, int rows, Map<String, Object> map) {
		PageInfo<Customer> pageInfo = new PageInfo<Customer>();
		pageInfo.setPage(page);
		Integer count = customerMapper.getCustomerCountByParams(map);
		if (count == null || count == 0) {
			pageInfo.setTotal(0);
			pageInfo.setRows(new ArrayList<Customer>());
			return pageInfo;
		}
		pageInfo.setTotal(count);
		page = (page <= 1) ? 1 : page;
		Pageable pageable = new PageRequest(page - 1, rows);

		List<Customer> list = customerMapper.getCustomerByParams(pageable, map);
		pageInfo.setRows(list);
		return pageInfo;
	}


}
