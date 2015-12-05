package com.queencastle.service.impl.bbs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.mapper.bbs.BBSArticleMapper;
import com.queencastle.dao.model.bbs.BBSArticle;
import com.queencastle.service.interf.bbs.BBSArticleService;

@Service
public class BBSArticleServiceImpl implements BBSArticleService {
	@Autowired
	private BBSArticleMapper bBSArticleMapper;

	@Override
	public BBSArticle getById(String id) {
		if (StringUtils.isNoneBlank(id)) {

			return bBSArticleMapper.getById(id);
		}
		return null;
	}

	@Override
	public int insert(BBSArticle bBSArticle) {
		return bBSArticleMapper.insert(bBSArticle);
	}

	@Override
	public PageInfo<BBSArticle> getBBSArticleByParams(int page, int rows, Map<String, Object> map) {
		PageInfo<BBSArticle> pageInfo = new PageInfo<BBSArticle>();
		pageInfo.setPage(page);
		Integer count = bBSArticleMapper.getBBSArticleCountByParams(map);
		if (count == null || count == 0) {
			pageInfo.setTotal(0);
			pageInfo.setRows(new ArrayList<BBSArticle>());
			return pageInfo;
		}
		pageInfo.setTotal(count);
		page = (page <= 1) ? 1 : page;
		Pageable pageable = new PageRequest(page - 1, rows);

		List<BBSArticle> list = bBSArticleMapper.getBBSArticleByParams(pageable, map);
		pageInfo.setRows(list);
		return pageInfo;
	}

}
