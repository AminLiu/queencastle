package com.queencastle.service.interf.data;

import java.util.List;
import java.util.Map;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.data.SaleData;

public interface SaleDataService {

    void insert(SaleData saleData);

    void batchInsert(List<SaleData> dataList);

    PageInfo<SaleData> getDatasByParam(int page, int rows, Map<String, Object> map);

}
