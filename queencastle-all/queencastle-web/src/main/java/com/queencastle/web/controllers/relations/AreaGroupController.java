package com.queencastle.web.controllers.relations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.area.City;
import com.queencastle.dao.model.area.Province;
import com.queencastle.dao.model.relations.AreaGroupInfo;
import com.queencastle.dao.model.relations.AreaType;
import com.queencastle.service.interf.area.AreaInfoService;
import com.queencastle.service.interf.relations.AreaGroupService;
import com.queencastle.web.vo.AreaGroupVO;
import com.queencastle.web.vo.AreaVO;

@Controller
@RequestMapping("/areaGroups")
public class AreaGroupController {

    @Autowired
    private AreaGroupService areaGroupService;
    @Autowired
    private AreaInfoService areaInfoService;

    @RequestMapping("/agList")
    public String index() {
        return "/relations/agList";
    }

    @ResponseBody
    @RequestMapping("/getAreaInfoByParams")
    public PageInfo<AreaVO> getAreaInfoByParams(int page, int rows, String type, String cname) {
        PageInfo<AreaVO> pageInfo = new PageInfo<AreaVO>();
        List<AreaVO> vos = new ArrayList<AreaVO>();
        if (StringUtils.equals(type, "province")) {
            PageInfo<Province> provinces = areaInfoService.getProvincePageData(page, rows, cname);
            pageInfo.setPage(provinces.getPage());
            pageInfo.setTotal(provinces.getTotal());
            List<Province> list = provinces.getRows();
            for (Province province : list) {
                AreaVO vo = convertProvince(province);
                vos.add(vo);
            }
        } else {
            PageInfo<City> citys = areaInfoService.getCityPageData(page, rows, cname);
            pageInfo.setPage(citys.getPage());
            pageInfo.setTotal(citys.getTotal());
            List<City> list = citys.getRows();
            for (City city : list) {
                AreaVO vo = convertCity(city);
                vos.add(vo);
            }
        }
        pageInfo.setRows(vos);
        return pageInfo;
    }

    private AreaVO convertProvince(Province province) {
        AreaVO vo = new AreaVO();
        vo.setId(province.getId());
        vo.setCname(province.getCname());
        vo.setEname(province.getEname());
        // 判断是否在配置表中存在
        AreaGroupInfo info = areaGroupService.getByAreaIdAndType(AreaType.province, vo.getId());
        if (info == null) {
            vo.setConfig(false);
        } else {
            vo.setConfig(true);
        }

        return vo;
    }

    private AreaVO convertCity(City city) {
        AreaVO vo = new AreaVO();
        vo.setId(city.getId());
        vo.setCname(city.getCname());
        vo.setEname(city.getEname());
        // 判断是否在配置表中存在
        AreaGroupInfo info = areaGroupService.getByAreaIdAndType(AreaType.city, vo.getId());
        if (info == null) {
            vo.setConfig(false);
        } else {
            vo.setConfig(true);
        }
        return vo;
    }


    @ResponseBody
    @RequestMapping("/getByParams")
    public PageInfo<AreaGroupVO> getByParams(int page, int rows, String type) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", type);
        PageInfo<AreaGroupInfo> results = areaGroupService.getByParams(page, rows, map);
        PageInfo<AreaGroupVO> pageInfo = new PageInfo<AreaGroupVO>();
        pageInfo.setPage(results.getPage());
        pageInfo.setTotal(results.getTotal());
        List<AreaGroupInfo> list = results.getRows();
        List<AreaGroupVO> vos = new ArrayList<AreaGroupVO>();
        if (!CollectionUtils.isEmpty(list)) {
            for (AreaGroupInfo info : list) {
                AreaGroupVO vo = convertToVO(info);
                vos.add(vo);
            }
        }
        pageInfo.setRows(vos);
        return pageInfo;
    }

    private AreaGroupVO convertToVO(AreaGroupInfo info) {
        AreaGroupVO vo = new AreaGroupVO();
        vo.setId(info.getId());
        String areaId = info.getAreaId();
        vo.setAreaId(areaId);
        vo.setCode(info.getCode());
        vo.setCreatedAt(info.getCreatedAt());
        AreaType type = info.getType();
        vo.setType(type);
        if (type == AreaType.province) {
            Province province = areaInfoService.getProvinceById(areaId);
            vo.setCname(province.getCname());
        } else if (type == AreaType.city) {
            City city = areaInfoService.getCityById(areaId);
            vo.setCname(city.getCname());
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping("/setAreaGroupCode")
    public boolean setAreaGroupCode(String pcode, String areaId, String type) {
        return areaGroupService.setAreaGroupCode(pcode, areaId, type);
    }

    @ResponseBody
    @RequestMapping("/getByProvinceIdAndType")
    public String getByProvinceIdAndType(String provinceId) {
        AreaGroupInfo info = areaGroupService.getByAreaIdAndType(AreaType.province, provinceId);
        return info.getCode();
    }

    @ResponseBody
    @RequestMapping("/getByCityIdAndType")
    public String getByCityIdAndType(String cityId) {
        AreaGroupInfo info = areaGroupService.getByAreaIdAndType(AreaType.city, cityId);
        return info.getCode();
    }

    @ResponseBody
    @RequestMapping("/getPrefix")
    public String getPrefix(String cityId) {
        City city = areaInfoService.getCityById(cityId);
        Province province = areaInfoService.getProvinceByprovinceCode(city.getProvinceCode());
        AreaGroupInfo info =
                areaGroupService.getByAreaIdAndType(AreaType.province, province.getId());
        if (info != null) {
            return info.getCode();
        }
        return null;
    }



}
