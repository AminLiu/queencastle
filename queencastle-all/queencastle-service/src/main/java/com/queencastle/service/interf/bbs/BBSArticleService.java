package com.queencastle.service.interf.bbs;

import java.util.Map;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.bbs.BBSArticle;

public interface BBSArticleService {
	BBSArticle getById(String id);

	int insert(BBSArticle bBSArticle);

	PageInfo<BBSArticle> getBBSArticleByParams(int page, int rows, Map<String, Object> map);

}
