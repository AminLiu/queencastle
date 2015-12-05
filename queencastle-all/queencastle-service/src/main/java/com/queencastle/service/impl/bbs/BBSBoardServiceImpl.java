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
import com.queencastle.dao.mapper.bbs.BBSBoardMapper;
import com.queencastle.dao.model.bbs.BBSBoard;
import com.queencastle.service.interf.bbs.BBSBoardService;

@Service
public class BBSBoardServiceImpl implements BBSBoardService {

	@Autowired
	private BBSBoardMapper bBSBoardMapper;

	@Override
	public BBSBoard getById(String id) {
		if (StringUtils.isNoneBlank(id)) {

			return bBSBoardMapper.getById(id);
		}
		return null;
	}

	@Override
	public int insert(BBSBoard bbsBoard) {
		return bBSBoardMapper.insert(bbsBoard);
	}

	@Override
	public PageInfo<BBSBoard> getBBSBoardByParams(int page, int rows, Map<String, Object> map) {
		PageInfo<BBSBoard> pageInfo = new PageInfo<BBSBoard>();
		pageInfo.setPage(page);
		Integer count = bBSBoardMapper.getBBSBoardCountByParams(map);
		if (count == null || count == 0) {
			pageInfo.setTotal(0);
			pageInfo.setRows(new ArrayList<BBSBoard>());
			return pageInfo;
		}
		pageInfo.setTotal(count);
		page = (page <= 1) ? 1 : page;
		Pageable pageable = new PageRequest(page - 1, rows);

		List<BBSBoard> list = bBSBoardMapper.getBBSBoardByParams(pageable, map);
		pageInfo.setRows(list);
		return pageInfo;
	}

}
