package com.queencastle.dao.mapper.shop;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import com.queencastle.dao.model.PropertyDict;
import com.queencastle.dao.model.PropertyDomain;

public interface PropertyDictMapper {
	int insertDomain(PropertyDomain domain);

	int insertDict(PropertyDict dict);

	PropertyDomain getDomainById(@Param("domainId") String domainId);

	PropertyDict getDictById(@Param("dictId") String dictId);

	List<PropertyDict> getListByDomainId(String domainId);

	Integer getpropertyDictCountByParams(@Param("map") Map<String, Object> map);

	List<PropertyDict> getPropertyDictByParams(@Param("page") Pageable pageable, @Param("map") Map<String, Object> map);

	Integer getpropertyDomainCountByParams(@Param("map") Map<String, Object> map);

	List<PropertyDomain> getPropertyDomainByParams(@Param("page") Pageable pageable,
			@Param("map") Map<String, Object> map);

}