package com.queencastle.service.interf;

import java.util.List;

import com.queencastle.dao.model.ZoneInfo;

public interface ZoneInfoService {
    int insert(ZoneInfo zoneInfo);

    /** 根据父级节点查询子节点 */
    List<ZoneInfo> getByParentId(String parentId);

    /** 获取迭代完整路径 */
    String getFullPath(String id);

}
