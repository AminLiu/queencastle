package com.queencastle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.queencastle.dao.mapper.UserMapper;
import com.queencastle.dao.mapper.relations.UserDetailInfoMapper;
import com.queencastle.dao.model.UserDetailInfo;
import com.queencastle.service.interf.UserDetailInfoService;

@Service
public class UserDetailInfoServiceImpl implements UserDetailInfoService {

    @Autowired
    private UserDetailInfoMapper userDetailInfoMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public int insert(UserDetailInfo userDetailInfo) {
        return userDetailInfoMapper.insert(userDetailInfo);
    }

    @Override
    public int updateById(UserDetailInfo userDetailInfo) {
        return userDetailInfoMapper.updateById(userDetailInfo);
    }

    @Override
    public UserDetailInfo getByUserId(String userId) {
        return userDetailInfoMapper.getByUserId(userId);
    }

    @Override
    public void updateOrInsertForTest(String userId) {
        UserDetailInfo detailInfo = getByUserId(userId);
        if (detailInfo == null) {
            detailInfo = new UserDetailInfo();
            detailInfo.setUserId(userId);
            detailInfo.setCityCode("330100");
            detailInfo.setProvinceCode("330000");
            insert(detailInfo);
        } else {
            detailInfo.setCityCode("330100");
            detailInfo.setProvinceCode("330000");
            updateById(detailInfo);
        }

    }

}
