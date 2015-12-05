package com.queencastle.service.impl.loggs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.queencastle.dao.mapper.loggs.UserLogMapper;
import com.queencastle.dao.model.loggs.UserLog;
import com.queencastle.service.interf.loggs.UserLogService;

@Service
public class UserLogServiceImpl implements UserLogService {
	@Autowired
	private UserLogMapper userLogMapper;

	@Override
	public int insert(UserLog userLog) {

		return userLogMapper.insert(userLog);

	}

}
