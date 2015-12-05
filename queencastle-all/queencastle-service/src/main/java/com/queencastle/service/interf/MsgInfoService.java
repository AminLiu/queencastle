package com.queencastle.service.interf;

import com.queencastle.dao.model.MsgInfo;

public interface MsgInfoService {
	
	MsgInfo getById(String id);
	
	int insert(MsgInfo msgInfo);
	
	int updateById(MsgInfo msgInfo);
	
	int deleteById(MsgInfo msgInfo);

}
