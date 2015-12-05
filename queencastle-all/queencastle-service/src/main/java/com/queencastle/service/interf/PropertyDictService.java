package com.queencastle.service.interf;

import java.util.List;
import java.util.Map;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.PropertyDict;
import com.queencastle.dao.model.PropertyDomain;

public interface PropertyDictService {

	int insertDomain(PropertyDomain domain);

	int insertDict(PropertyDict dict);

	PropertyDomain getDomainById(String domainId);

	PropertyDict getDictById(String dictId);

	List<PropertyDict> getListByDomainId(String domainId);

	PageInfo<PropertyDict> getPropertyDictByParams(int page, int rows, Map<String, Object> map);

	PageInfo<PropertyDomain> getPropertyDomainByParams(int page, int rows, Map<String, Object> map);
}
