package com.queencastle.service.impl.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.mapper.data.SaleDataMapper;
import com.queencastle.dao.model.data.SaleData;
import com.queencastle.service.interf.data.SaleDataService;

@Service
public class SaleDataServiceImpl implements SaleDataService {

    @Autowired
    private SaleDataMapper saleDataMapper;

    @Override
    public void insert(SaleData saleData) {
        saleDataMapper.insert(saleData);
    }

    @Override
    public void batchInsert(List<SaleData> dataList) {
        saleDataMapper.batchInsert(dataList);
    }

    @Override
    public PageInfo<SaleData> getDatasByParam(int page, int rows, Map<String, Object> map) {
        PageInfo<SaleData> pageInfo = new PageInfo<SaleData>();
        pageInfo.setPage(page);
        Integer count = saleDataMapper.getDataCountByParam(map);
        if (count == null || count == 0) {
            pageInfo.setTotal(0);
            pageInfo.setRows(new ArrayList<SaleData>());
            return pageInfo;
        }
        pageInfo.setTotal(count);
        page = (page <= 1) ? 1 : page;
        Pageable pageable = new PageRequest(page - 1, rows);

        List<SaleData> list = saleDataMapper.getDatasByParam(pageable, map);
        pageInfo.setRows(list);
        return pageInfo;
    }

}
