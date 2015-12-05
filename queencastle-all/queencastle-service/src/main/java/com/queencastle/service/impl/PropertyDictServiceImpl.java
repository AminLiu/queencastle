package com.queencastle.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.mapper.shop.PropertyDictMapper;
import com.queencastle.dao.model.PropertyDict;
import com.queencastle.dao.model.PropertyDomain;
import com.queencastle.service.interf.PropertyDictService;

@Service
public class PropertyDictServiceImpl implements PropertyDictService {

	@Autowired
	private PropertyDictMapper propertyDictMapper;

	@Override
	public int insertDomain(PropertyDomain domain) {
		return propertyDictMapper.insertDomain(domain);
	}

	@Override
	public int insertDict(PropertyDict dict) {
		return propertyDictMapper.insertDict(dict);
	}

	@Override
	public PropertyDomain getDomainById(String domainId) {
		if (StringUtils.isNoneBlank(domainId)) {
			return propertyDictMapper.getDomainById(domainId);
		}
		return null;
	}

	@Override
	public PropertyDict getDictById(String dictId) {
		if (StringUtils.isNoneBlank(dictId)) {
			return propertyDictMapper.getDictById(dictId);
		}
		return null;
	}

	@Override
	public List<PropertyDict> getListByDomainId(String domainId) {
		if (StringUtils.isNoneBlank(domainId)) {
			return propertyDictMapper.getListByDomainId(domainId);
		}
		return null;
	}

	@Override
	public PageInfo<PropertyDict> getPropertyDictByParams(int page, int rows, Map<String, Object> map) {
		PageInfo<PropertyDict> pageInfo = new PageInfo<PropertyDict>();
		pageInfo.setPage(page);
		Integer count = propertyDictMapper.getpropertyDictCountByParams(map);
		if (count == null || count == 0) {
			pageInfo.setTotal(0);
			pageInfo.setRows(new ArrayList<PropertyDict>());
			return pageInfo;
		}
		pageInfo.setTotal(count);
		page = (page <= 1) ? 1 : page;
		Pageable pageable = new PageRequest(page - 1, rows);

		List<PropertyDict> list = propertyDictMapper.getPropertyDictByParams(pageable, map);
		pageInfo.setRows(list);
		return pageInfo;
	}

	@Override
	public PageInfo<PropertyDomain> getPropertyDomainByParams(int page, int rows, Map<String, Object> map) {
		PageInfo<PropertyDomain> pageInfo = new PageInfo<PropertyDomain>();
		pageInfo.setPage(page);
		Integer count = propertyDictMapper.getpropertyDomainCountByParams(map);
		if (count == null || count == 0) {
			pageInfo.setTotal(0);
			pageInfo.setRows(new ArrayList<PropertyDomain>());
			return pageInfo;
		}
		pageInfo.setTotal(count);
		page = (page <= 1) ? 1 : page;
		Pageable pageable = new PageRequest(page - 1, rows);

		List<PropertyDomain> list = propertyDictMapper.getPropertyDomainByParams(pageable, map);
		pageInfo.setRows(list);
		return pageInfo;
	}

}
