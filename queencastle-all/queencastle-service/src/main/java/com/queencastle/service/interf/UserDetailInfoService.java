package com.queencastle.service.interf;

import org.apache.ibatis.annotations.Param;

import com.queencastle.dao.model.UserDetailInfo;

public interface UserDetailInfoService {

    UserDetailInfo getByUserId(@Param("userId") String userId);


    int insert(UserDetailInfo userDetailInfo);

    int updateById(UserDetailInfo userDetailInfo);


    void updateOrInsertForTest(String userId);
}
