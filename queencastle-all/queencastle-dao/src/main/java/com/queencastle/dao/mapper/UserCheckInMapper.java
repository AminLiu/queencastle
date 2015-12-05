package com.queencastle.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.queencastle.dao.model.UserCheckIn;

public interface UserCheckInMapper {

    int insert(UserCheckIn userCheckIn);

    List<UserCheckIn> getByUserId(@Param("userId") String userId);

}
