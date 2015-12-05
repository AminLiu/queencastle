package com.queencastle.web.controllers.data;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.data.SaleData;
import com.queencastle.service.interf.data.SaleDataService;

@Controller
@RequestMapping("/data")
public class SaleDataController {

    @Autowired
    private SaleDataService saleDataService;

    @RequestMapping("/index")
    public String index() {
        return "data/saleDataList";
    }

    @ResponseBody
    @RequestMapping("/getDatasByParam")
    public PageInfo<SaleData> getDatasByParam(int page, int rows) {
        Map<String, Object> map = new HashMap<String, Object>();
        PageInfo<SaleData> pageInfo = saleDataService.getDatasByParam(page, rows, map);
        return pageInfo;
    }
}
