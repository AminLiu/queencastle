package com.queencastle.web.controllers.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.queencastle.dao.model.area.Area;
import com.queencastle.dao.model.area.City;
import com.queencastle.dao.model.area.Province;
import com.queencastle.service.interf.area.AreaInfoService;

@Controller
@RequestMapping("/area")
public class AreaInfoController {

    @Autowired
    private AreaInfoService areaInfoService;

    @ResponseBody
    @RequestMapping("/getAllProvince")
    public List<Province> getAllProvince() {
        return areaInfoService.getAllProvinces();
    }

    @ResponseBody
    @RequestMapping("/getByProvinceId")
    public List<City> getByProvinceId(String provinceId) {
        Province province = areaInfoService.getProvinceById(provinceId);
        List<City> citys = areaInfoService.getByProvinceCode(province.getCode());
        return citys;
    }

    @ResponseBody
    @RequestMapping("/getByProvinceCode")
    public List<City> getByProvinceCode(String provinceCode) {
        List<City> citys = areaInfoService.getByProvinceCode(provinceCode);
        return citys;
    }

    @ResponseBody
    @RequestMapping("/getAreasByCityCode")
    public List<Area> getByCityCode(String cityCode) {
        List<Area> areas = areaInfoService.getAreasByCityCode(cityCode);
        return areas;
    }
}
