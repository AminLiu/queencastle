package com.queencastle.service.interf.bbs;

import java.util.Map;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.bbs.BBSBoard;

public interface BBSBoardService {
	BBSBoard getById(String id);

	int insert(BBSBoard bbsBoard);

	PageInfo<BBSBoard> getBBSBoardByParams(int page, int rows, Map<String, Object> map);

}
