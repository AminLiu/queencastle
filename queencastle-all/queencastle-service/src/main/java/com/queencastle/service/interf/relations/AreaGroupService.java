package com.queencastle.service.interf.relations;

import java.util.Map;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.relations.AreaGroupInfo;
import com.queencastle.dao.model.relations.AreaType;

public interface AreaGroupService {

    int insert(AreaGroupInfo areaGroup);

    AreaGroupInfo getById(String id);

    PageInfo<AreaGroupInfo> getByParams(int page, int rows, Map<String, Object> map);

    AreaGroupInfo getByAreaIdAndType(AreaType type, String areaId);

    boolean setAreaGroupCode(String pcode, String areaId, String type);
}
