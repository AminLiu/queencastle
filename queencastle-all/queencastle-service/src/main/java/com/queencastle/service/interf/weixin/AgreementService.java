package com.queencastle.service.interf.weixin;

import java.util.List;

import com.queencastle.dao.model.weixin.Agreement;

public interface AgreementService {
	int insert(Agreement agreement);

	List<String> getByUserId(String userId);
}
