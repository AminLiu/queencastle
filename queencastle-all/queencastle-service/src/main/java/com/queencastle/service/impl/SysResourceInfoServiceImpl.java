package com.queencastle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.queencastle.dao.mapper.SysResourceInfoMapper;
import com.queencastle.dao.model.SysResourceInfo;
import com.queencastle.service.interf.SysResourceInfoService;

@Service
public class SysResourceInfoServiceImpl implements SysResourceInfoService {

    @Autowired
    private SysResourceInfoMapper sysResourceInfoMapper;

    @Override
    public int insert(SysResourceInfo resourceInfo) {
        return sysResourceInfoMapper.insert(resourceInfo);
    }

}
