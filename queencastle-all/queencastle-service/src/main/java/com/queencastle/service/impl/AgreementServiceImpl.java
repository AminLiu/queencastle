package com.queencastle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.queencastle.dao.mapper.AgreementMapper;
import com.queencastle.dao.model.weixin.Agreement;
import com.queencastle.service.interf.weixin.AgreementService;

@Service
public class AgreementServiceImpl implements AgreementService {
	@Autowired
	private AgreementMapper agreementMapper;

	@Override
	public int insert(Agreement agreement) {
		return agreementMapper.insert(agreement);
	}

	@Override
	public List<String> getByUserId(String userId) {
		return agreementMapper.getByUserId(userId);
	}

}
