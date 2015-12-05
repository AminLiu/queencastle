package com.queencastle.service.impl.weixin;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.queencastle.dao.mapper.weixin.UserIntentionMapper;
import com.queencastle.dao.model.weixin.UserIntention;
import com.queencastle.service.interf.weixin.UserIntentionService;

@Service
public class UserIntentionServiceImpl implements UserIntentionService {

    @Autowired
    private UserIntentionMapper userIntentionMapper;

    @Override
    public int insert(UserIntention intention) {
        return userIntentionMapper.insert(intention);
    }

    @Override
    public UserIntention getById(String id) {
        if (StringUtils.isNoneBlank(id)) {
            return userIntentionMapper.getById(id);
        }
        return null;
    }

}
