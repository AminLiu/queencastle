package com.queencastle.service.interf.weixin;

import com.queencastle.dao.model.weixin.UserIntention;

public interface UserIntentionService {

    int insert(UserIntention intention);

    UserIntention getById(String id);
}
