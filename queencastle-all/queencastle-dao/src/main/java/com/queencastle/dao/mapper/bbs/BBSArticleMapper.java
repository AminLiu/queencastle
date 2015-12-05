package com.queencastle.dao.mapper.bbs;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import com.queencastle.dao.model.bbs.BBSArticle;

public interface BBSArticleMapper {

	BBSArticle getById(@Param("id") String id);

	int insert(BBSArticle bBSArticle);

	Integer getBBSArticleCountByParams(@Param("map") Map<String, Object> map);

	List<BBSArticle> getBBSArticleByParams(@Param("page") Pageable pageable, @Param("map") Map<String, Object> map);

}
