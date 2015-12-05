package com.queencastle.dao.mapper.shop;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import com.queencastle.dao.model.shop.Customer;

public interface CustomerMapper {
	Customer getById(@Param("id") String id);

	int insert(Customer customer);

	Integer getCustomerCountByParams(@Param("map") Map<String, Object> map);

	List<Customer> getCustomerByParams(@Param("page") Pageable pageable, @Param("map") Map<String, Object> map);

}
