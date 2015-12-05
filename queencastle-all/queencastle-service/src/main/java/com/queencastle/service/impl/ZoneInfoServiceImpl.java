package com.queencastle.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.queencastle.dao.model.ZoneInfo;
import com.queencastle.service.interf.ZoneInfoService;

@Service
public class ZoneInfoServiceImpl implements ZoneInfoService {

    @Override
    public int insert(ZoneInfo zoneInfo) {
        return 0;
    }

    @Override
    public List<ZoneInfo> getByParentId(String parentId) {
        return null;
    }

    @Override
    public String getFullPath(String id) {
        return null;
    }

}
