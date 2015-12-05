package com.queencastle.service.impl.area;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.mapper.area.AreaInfoMapper;
import com.queencastle.dao.model.area.Area;
import com.queencastle.dao.model.area.City;
import com.queencastle.dao.model.area.Province;
import com.queencastle.dao.utils.jedis.ObjectJedisCache;
import com.queencastle.service.interf.area.AreaInfoService;

@Service
public class AreaInfoServiceImpl implements AreaInfoService {
    @Autowired
    private ObjectJedisCache provinceCache;
    @Autowired
    private ObjectJedisCache cityCache;
    @Autowired
    private ObjectJedisCache provinceCodeCache;
    @Autowired
    private ObjectJedisCache cityCodeCache;
    @Autowired
    private AreaInfoMapper areaInfoMapper;

    @Autowired
    private ObjectJedisCache cityListCache;


    @Autowired
    private ObjectJedisCache areaListCache;

    @Override
    public int normalizeProvince(Province province) {
        return areaInfoMapper.normalizeProvince(province);
    }

    @Override
    public int normalizeCity(City city) {
        return areaInfoMapper.normalizeCity(city);
    }

    @Override
    public int normalizeArea(Area area) {
        return areaInfoMapper.normalizeArea(area);
    }

    @Override
    public List<Province> getAllProvinces() {
        return areaInfoMapper.getAllProvinces();
    }

    @Override
    public List<City> getByProvinceId(String provinceId) {
        Province province = getProvinceById(provinceId);
        if (province != null) {
            return areaInfoMapper.getByProvinceCode(province.getCode());
        }
        return null;
    }

    @Override
    public Province getProvinceById(String id) {
        if (StringUtils.isNoneBlank(id)) {
            Province province = (Province) provinceCache.getObj(id);
            if (province == null) {
                province = areaInfoMapper.getProvinceById(id);
                if (province != null) {
                    provinceCache.setObj(id, province);
                }
            }
            return province;
        }
        return null;
    }

    @Override
    public City getCityById(String id) {
        if (StringUtils.isNoneBlank(id)) {
            City city = (City) cityCache.getObj(id);
            if (city == null) {
                city = areaInfoMapper.getCityById(id);
                if (city != null) {
                    cityCache.setObj(id, city);
                }
            }
            return city;
        }
        return null;
    }

    @Override
    public List<City> getByProvinceCode(String provinceCode) {
        if (StringUtils.isNoneBlank(provinceCode)) {
            List<City> citys = (List<City>) cityListCache.getObj(provinceCode);
            if (CollectionUtils.isEmpty(citys)) {
                citys = areaInfoMapper.getByProvinceCode(provinceCode);
                cityListCache.setObj(provinceCode, citys);
            }
            return citys;

        }
        return null;
    }

    @Override
    public Province getProvinceByprovinceCode(String provinceCode) {
        Province province = (Province) provinceCodeCache.getObj(provinceCode);
        if (province == null) {
            province = areaInfoMapper.getProvinceByprovinceCode(provinceCode);
            if (province != null) {
                provinceCodeCache.setObj(provinceCode, province);
            }
        }
        return province;
    }

    @Override
    public City getByCityCode(String cityCode) {
        City city = (City) cityCodeCache.getObj(cityCode);
        if (city == null) {
            city = areaInfoMapper.getByCityCode(cityCode);
            if (city != null) {
                cityCodeCache.setObj(cityCode, city);
            }
        }
        return city;
    }

    @Override
    public PageInfo<Province> getProvincePageData(int page, int rows, String cname) {
        PageInfo<Province> pageInfo = new PageInfo<Province>();
        pageInfo.setPage(page);
        Integer count = areaInfoMapper.getProvincesCount(cname);
        if (count == null || count == 0) {
            pageInfo.setTotal(0);
            pageInfo.setRows(new ArrayList<Province>());
            return pageInfo;
        }
        pageInfo.setTotal(count);
        page = (page <= 1) ? 1 : page;
        Pageable pageable = new PageRequest(page - 1, rows);

        List<Province> list = areaInfoMapper.getProvinces(pageable, cname);
        pageInfo.setRows(list);
        return pageInfo;
    }

    @Override
    public PageInfo<City> getCityPageData(int page, int rows, String cname) {
        PageInfo<City> pageInfo = new PageInfo<City>();
        pageInfo.setPage(page);
        Integer count = areaInfoMapper.getCitysCount(cname);
        if (count == null || count == 0) {
            pageInfo.setTotal(0);
            pageInfo.setRows(new ArrayList<City>());
            return pageInfo;
        }
        pageInfo.setTotal(count);
        page = (page <= 1) ? 1 : page;
        Pageable pageable = new PageRequest(page - 1, rows);

        List<City> list = areaInfoMapper.getCitys(pageable, cname);
        pageInfo.setRows(list);
        return pageInfo;
    }

    @Override
    public List<Area> getAreasByCityCode(String cityCode) {

        if (StringUtils.isNoneBlank(cityCode)) {
            List<Area> areas = (List<Area>) areaListCache.getObj(cityCode);
            if (CollectionUtils.isEmpty(areas)) {
                areas = areaInfoMapper.getAreasByCityCode(cityCode);
                areaListCache.setObj(cityCode, areas);
            }
            return areas;
        }

        return null;
    }

    @Override
    public List<Area> getAllAreas() {
        return areaInfoMapper.getAllAreas();
    }

    @Override
    public Area getAllAreasByAreaCode(String areaCode) {
        return areaInfoMapper.getAllAreasByAreaCode(areaCode);
    }

}
