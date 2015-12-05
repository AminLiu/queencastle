package com.queencastle.dao.mapper.data;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import com.queencastle.dao.model.data.SaleData;

public interface SaleDataMapper {

    public void insert(SaleData saleData);

    public void batchInsert(@Param("dataList") List<SaleData> dataList);

    public Integer getDataCountByParam(@Param("dataList") Map<String, Object> map);

    public List<SaleData> getDatasByParam(@Param("page") Pageable pageable,
            @Param("map") Map<String, Object> map);
}
