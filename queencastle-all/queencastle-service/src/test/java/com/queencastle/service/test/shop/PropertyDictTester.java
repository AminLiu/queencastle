package com.queencastle.service.test.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.PropertyDict;
import com.queencastle.dao.model.PropertyDomain;
import com.queencastle.dao.mybatis.IdTypeHandler;
import com.queencastle.service.test.BaseTest;

public class PropertyDictTester extends BaseTest {

	@Test
	@Ignore
	public void domains() {
		List<String> cnames = new ArrayList<String>();
		cnames.add("从事微商时间");
		cnames.add("代理人数");
		cnames.add("年销售额");
		for (String cname : cnames) {
			insertDomain(cname);
		}

	}

	public void insertDomain(String cname) {
		PropertyDomain domain = new PropertyDomain();
		domain.setCname(cname);
		propertyDictService.insertDomain(domain);
	}

	@Test
	@Ignore
	public void dicts() {
		List<String> cnames = new ArrayList<String>();
		cnames.add("新人刚开始做");
		cnames.add("一到三个月");
		cnames.add("一到两年");
		cnames.add("三年以上");
		String domainId = IdTypeHandler.encode(1);
		for (String cname : cnames) {
			insertDict(cname, domainId);
		}

		cnames.add("0人");
		cnames.add("3到5人");
		cnames.add("10人团队");
		cnames.add("30人以上");
		domainId = IdTypeHandler.encode(2);
		for (String cname : cnames) {
			insertDict(cname, domainId);
		}

		cnames.add("0");
		cnames.add("1000-3000");
		cnames.add("3000-10000");
		cnames.add("10000-50000");
		cnames.add("50000+");
		domainId = IdTypeHandler.encode(3);
		for (String cname : cnames) {
			insertDict(cname, domainId);
		}

	}

	public void insertDict(String cname, String domainId) {
		PropertyDict dict = new PropertyDict();
		dict.setCname(cname);
		dict.setDomainId(domainId);
		propertyDictService.insertDict(dict);
	}

	@Test
	// @Ignore
	public void getPropertyDictPageinfo() {

		Map<String, Object> map = new HashMap<>();
		PageInfo<PropertyDict> result = propertyDictService.getPropertyDictByParams(1, 3, map);
		System.out.println("===========" + result.getPage());
		System.out.println("==========" + result.getTotal());
	}

	@Test
	@Ignore
	public void getgetPropertyDomainPageinfo() {

		Map<String, Object> map = new HashMap<>();
		PageInfo<PropertyDomain> result = propertyDictService.getPropertyDomainByParams(1, 3, map);
		System.out.println("===========" + result.getPage());
		System.out.println("==========" + result.getTotal());
	}

}
