package com.queencastle.service.impl.relations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.mapper.relations.AreaGroupMapper;
import com.queencastle.dao.model.relations.AreaGroupInfo;
import com.queencastle.dao.model.relations.AreaType;
import com.queencastle.service.interf.relations.AreaGroupService;

@Service
public class AreaGroupServiceImpl implements AreaGroupService {

    @Autowired
    private AreaGroupMapper areaGroupMapper;

    @Override
    public int insert(AreaGroupInfo areaGroup) {

        return areaGroupMapper.insert(areaGroup);
    }

    @Override
    public AreaGroupInfo getById(String id) {
        if (StringUtils.isNoneBlank(id)) {
            return areaGroupMapper.getById(id);
        }
        return null;
    }

    @Override
    public PageInfo<AreaGroupInfo> getByParams(int page, int rows, Map<String, Object> map) {
        PageInfo<AreaGroupInfo> pageInfo = new PageInfo<AreaGroupInfo>();
        pageInfo.setPage(page);
        Integer count = areaGroupMapper.getResultsCountByParams(map);
        if (count == null || count == 0) {
            pageInfo.setTotal(0);
            pageInfo.setRows(new ArrayList<AreaGroupInfo>());
            return pageInfo;
        }
        pageInfo.setTotal(count);
        page = (page <= 1) ? 1 : page;
        Pageable pageable = new PageRequest(page - 1, rows);
        List<AreaGroupInfo> list = areaGroupMapper.getResultsByParams(pageable, map);
        pageInfo.setRows(list);
        return pageInfo;
    }

    @Override
    public AreaGroupInfo getByAreaIdAndType(AreaType type, String areaId) {
        if (StringUtils.isNoneBlank(areaId)) {
            return areaGroupMapper.getByAreaIdAndType(type, areaId);
        }
        return null;
    }

    @Override
    public boolean setAreaGroupCode(String pcode, String areaId, String type) {
        AreaGroupInfo info = new AreaGroupInfo();
        if (StringUtils.equals(type, "province")) {
            info.setType(AreaType.province);
        } else if (StringUtils.equals(type, "city")) {
            info.setType(AreaType.city);
        }
        info.setAreaId(areaId);
        info.setCode(pcode);
        insert(info);
        return false;
    }

}
