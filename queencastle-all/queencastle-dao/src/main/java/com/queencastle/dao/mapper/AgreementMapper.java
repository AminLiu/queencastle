package com.queencastle.dao.mapper;

import java.util.List;

import com.queencastle.dao.model.weixin.Agreement;

public interface AgreementMapper {

	int insert(Agreement agreement);

	List<String> getByUserId(String userId);

}
