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
import com.queencastle.dao.mapper.bbs.BBSCommentMapper;
import com.queencastle.dao.model.bbs.BBSComment;
import com.queencastle.service.interf.bbs.BBSCommentService;

@Service
public class BBSCommentServiceImpl implements BBSCommentService {
	@Autowired
	private BBSCommentMapper bBSCommentMapper;

	@Override
	public BBSComment getById(String id) {
		if (StringUtils.isNoneBlank(id)) {

			return bBSCommentMapper.getById(id);
		}
		return null;
	}

	@Override
	public int insert(BBSComment bbsComment) {
		return bBSCommentMapper.insert(bbsComment);
	}

	@Override
	public PageInfo<BBSComment> getBBSCommentByParams(int page, int rows, Map<String, Object> map) {
		PageInfo<BBSComment> pageInfo = new PageInfo<BBSComment>();
		pageInfo.setPage(page);
		Integer count = bBSCommentMapper.getBBSCommentCountByParams(map);
		if (count == null || count == 0) {
			pageInfo.setTotal(0);
			pageInfo.setRows(new ArrayList<BBSComment>());
			return pageInfo;
		}
		pageInfo.setTotal(count);
		page = (page <= 1) ? 1 : page;
		Pageable pageable = new PageRequest(page - 1, rows);

		List<BBSComment> list = bBSCommentMapper.getBBSCommentByParams(pageable, map);
		pageInfo.setRows(list);
		return pageInfo;
	}

}
