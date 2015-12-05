package com.queencastle.service.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

import com.queencastle.dao.model.area.Area;
import com.queencastle.dao.model.area.City;
import com.queencastle.dao.model.area.Province;
import com.queencastle.dao.utils.PinYinUtils;

public class AreaInfoTester extends BaseTest {

	@Test
	@Ignore
	public void testProvince() {
		List<Province> list = areaInfoService.getAllProvinces();
		for (Province province : list) {
			String cname = province.getCname();
			System.out.println(cname);
			String ename = PinYinUtils.fullPinyin(cname);
			province.setEname(ename);
			areaInfoService.normalizeProvince(province);
		}
	}

	/*
	 * @Test
	 * 
	 * @Ignore public void testinser() { UserGoodsAddress userGoodsAddress=new
	 * UserGoodsAddress();
	 * 
	 * userGoodsAddress.setProvince(areaInfoService.getProvinceByprovinceCode(
	 * provinceCode)); userGoodsAddressService.insert(userGoodsAddress); } }
	 */
	@Test
	@Ignore
	public void testCity() {
		List<Province> list = areaInfoService.getAllProvinces();
		for (Province province : list) {
			List<City> citys = areaInfoService.getByProvinceId(province.getId());
			for (City city : citys) {
				String cname = city.getCname();
				System.out.println(cname);
				String ename = PinYinUtils.fullPinyin(cname);
				city.setEname(ename);
				areaInfoService.normalizeCity(city);

			}
		}
	}

	@Test
	@Ignore
	public void testSaveProvinceCache() {
		List<Province> list = areaInfoService.getAllProvinces();
		for (Province province : list) {
			provinceCache.setObj(province.getId(), province);
		}
	}

	@Test
	@Ignore
	public void testGetProvinceCache() {
		List<Province> list = areaInfoService.getAllProvinces();
		for (Province province : list) {
			Province area = (Province) provinceCache.getObj(province.getId());
			System.out.println(area.getCname() + "-->" + area.getEname());
		}
	}

	@Test
	public void testAreaInfo() {
		List<String> list = new ArrayList<String>();
		List<Area> areas = areaInfoService.getAllAreas();
		for (Area area : areas) {
			String cname = area.getCname();
			cname = StringUtils.deleteWhitespace(cname);
			System.out.println(cname);
			try {
				area.setEname(PinYinUtils.fullPinyin(cname));
			} catch (Exception e) {
				System.out.println(cname);
				list.add(cname);
			}
			areaInfoService.normalizeArea(area);
		}
		try {
			FileUtils.writeLines(new File("d:/abc.log"), list);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
