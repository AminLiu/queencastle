package com.queencastle.web.controllers.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.PropertyDict;
import com.queencastle.dao.model.PropertyDomain;
import com.queencastle.service.interf.PropertyDictService;
import com.queencastle.web.vo.PropertyVO;

@Controller
@RequestMapping("/shop")
public class PropertyDictController {
	@Autowired
	private PropertyDictService propertyDictService;

	@RequestMapping("/propertyDict")
	public String PropertyDictList() {
		return "/shop/propertyDictList";
	}

	@ResponseBody
	@RequestMapping("/getPropertyByParam")
	public PageInfo<PropertyVO> getPropertyByParam(int page, int rows, String id) {
		if (StringUtils.isBlank(id)) {
			id = "1";
		}

		List<PropertyDict> list = propertyDictService.getListByDomainId(id);
		PageInfo<PropertyVO> pageInfo = new PageInfo<>();
		List<PropertyVO> vos = new ArrayList<PropertyVO>();
		Map<String, Object> map = new HashMap<String, Object>();
		PageInfo<PropertyDict> property = propertyDictService.getPropertyDictByParams(page, rows, map);
		pageInfo.setPage(property.getPage());
		pageInfo.setTotal(property.getTotal());
		// List<PropertyDict> list = property.getRows();
		for (PropertyDict propertyDict : list) {
			PropertyVO vo = convertPropertyDict(propertyDict);
			vos.add(vo);
		}
		pageInfo.setRows(vos);
		return pageInfo;

	}

	private PropertyVO convertPropertyDict(PropertyDict propertyDict) {
		PropertyVO propertyVO = new PropertyVO();
		propertyVO.setId(propertyDict.getId());
		propertyVO.setCname(propertyDict.getCname());
		propertyVO.setDomainId(propertyDict.getDomainId());
		propertyVO.setCreatedAt(propertyDict.getCreatedAt());
		return propertyVO;

	}

	@ResponseBody
	@RequestMapping("/getDomainByParam")
	public PageInfo<PropertyDomain> getDomainByParam(int page, int rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageInfo<PropertyDomain> pageInfo = propertyDictService.getPropertyDomainByParams(page, rows, map);
		return pageInfo;

	}

	// 增加子类

	@ResponseBody
	@RequestMapping("/addPropertyDict")
	public PropertyDomain addPropertyDict(String id) {
		if (StringUtils.isNoneBlank(id)) {
			PropertyDomain result = propertyDictService.getDomainById(id);

			return result;
		}
		return null;
	}

	// 保存到子类

	@ResponseBody
	@RequestMapping("/savePropertyDict")

	public boolean savePropertyDict(String id, String cname) {
		PropertyDict propertyDict = new PropertyDict();
		propertyDict.setDomainId(id);
		propertyDict.setCname(cname);
		propertyDictService.insertDict(propertyDict);

		return true;

	}

}
