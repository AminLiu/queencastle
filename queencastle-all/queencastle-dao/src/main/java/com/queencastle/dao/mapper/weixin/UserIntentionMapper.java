package com.queencastle.dao.mapper.weixin;

import org.apache.ibatis.annotations.Param;

import com.queencastle.dao.model.weixin.UserIntention;

public interface UserIntentionMapper {

    int insert(UserIntention intention);

    UserIntention getById(@Param("id") String id);
}
