package com.queencastle.dao.mapper.area;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import com.queencastle.dao.model.area.Area;
import com.queencastle.dao.model.area.City;
import com.queencastle.dao.model.area.Province;

public interface AreaInfoMapper {

	int normalizeProvince(Province province);

	int normalizeCity(City city);

	int normalizeArea(Area area);

	List<Province> getAllProvinces();

	List<City> getByProvinceCode(@Param("provinceCode") String provinceCode);

	Province getProvinceById(@Param("id") String id);

	City getCityById(@Param("id") String id);

	Province getProvinceByprovinceCode(String provinceCode);

	City getByCityCode(String cityCode);

	Integer getProvincesCount(@Param("cname") String cname);

	List<Province> getProvinces(@Param("page") Pageable pageable, @Param("cname") String cname);

	Integer getCitysCount(@Param("cname") String cname);

	List<City> getCitys(@Param("page") Pageable pageable, @Param("cname") String cname);

	List<Area> getAreasByCityCode(@Param("cityCode") String cityCode);

	List<Area> getAllAreas();

	Area getAllAreasByAreaCode(String areaCode);

}
