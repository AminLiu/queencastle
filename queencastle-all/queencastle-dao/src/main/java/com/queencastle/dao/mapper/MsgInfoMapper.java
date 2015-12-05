package com.queencastle.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.queencastle.dao.model.MsgInfo;

public interface MsgInfoMapper {
	
	MsgInfo getById(@Param("id")String id);
	
	int insert(MsgInfo msgInfo);
	
	int updateById(MsgInfo msgInfo);
//	
//	int deleteById(MsgInfo msgInfo);
}
