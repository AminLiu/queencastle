package com.queencastle.service.interf.bbs;

import java.util.Map;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.bbs.BBSComment;

public interface BBSCommentService {
	BBSComment getById(String id);

	int insert(BBSComment bbsComment);

	PageInfo<BBSComment> getBBSCommentByParams(int page, int rows, Map<String, Object> map);
}
