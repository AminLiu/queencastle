package com.queencastle.dao.mapper.relations;

import org.apache.ibatis.annotations.Param;

import com.queencastle.dao.model.UserDetailInfo;

public interface UserDetailInfoMapper {
	UserDetailInfo getById(@Param("id") String id);
	
	UserDetailInfo getByUserId(@Param("userId")String userId);

    int insert(UserDetailInfo userDetailInfo);

    int updateById(UserDetailInfo userDetailInfo);
}
