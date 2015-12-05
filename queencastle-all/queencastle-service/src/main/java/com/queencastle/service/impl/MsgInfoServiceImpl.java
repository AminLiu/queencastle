package com.queencastle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.queencastle.dao.mapper.MsgInfoMapper;
import com.queencastle.dao.model.MsgInfo;
import com.queencastle.service.interf.MsgInfoService;
@Service
public class MsgInfoServiceImpl implements MsgInfoService {
	
	@Autowired
	private MsgInfoMapper msgInfoMapper;
	@Override
	public int insert(MsgInfo msgInfo) {
	
		return msgInfoMapper.insert(msgInfo);
		
	}

	@Override
	public int updateById(MsgInfo msgInfo) {
		
		return msgInfoMapper.updateById(msgInfo);
		
	}

	@Override
	public int deleteById(MsgInfo msgInfo) {
		
		//return msgInfoMapper.deleteById(msgInfo);
		return 0;
	}

	@Override
	public MsgInfo getById(String id) {
		
		return msgInfoMapper.getById(id);
	}

}
