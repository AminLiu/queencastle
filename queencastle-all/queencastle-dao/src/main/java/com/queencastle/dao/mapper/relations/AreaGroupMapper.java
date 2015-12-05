package com.queencastle.dao.mapper.relations;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import com.queencastle.dao.model.relations.AreaGroupInfo;
import com.queencastle.dao.model.relations.AreaType;

public interface AreaGroupMapper {
    int insert(AreaGroupInfo areaGroup);

    AreaGroupInfo getById(@Param("id") String id);

    Integer getResultsCountByParams(@Param("map") Map<String, Object> map);

    List<AreaGroupInfo> getResultsByParams(@Param("page") Pageable pageable,
            @Param("map") Map<String, Object> map);

    AreaGroupInfo getByAreaIdAndType(@Param("type") AreaType type, @Param("areaId") String areaId);

}
