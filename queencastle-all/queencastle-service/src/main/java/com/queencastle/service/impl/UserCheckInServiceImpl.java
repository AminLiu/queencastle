package com.queencastle.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.queencastle.dao.mapper.UserCheckInMapper;
import com.queencastle.dao.model.UserCheckIn;
import com.queencastle.service.interf.UserCheckInService;

@Service
public class UserCheckInServiceImpl implements UserCheckInService {

    @Autowired
    private UserCheckInMapper userCheckInMapper;

    @Override
    public int insert(UserCheckIn userCheckIn) {
        return userCheckInMapper.insert(userCheckIn);
    }

    @Override
    public List<UserCheckIn> getByUserId(String userId) {
        if (StringUtils.isNoneBlank(userId)) {
            return userCheckInMapper.getByUserId(userId);

        }
        return null;
    }

}
