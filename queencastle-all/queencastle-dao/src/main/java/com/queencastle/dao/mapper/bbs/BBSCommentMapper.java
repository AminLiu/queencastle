package com.queencastle.dao.mapper.bbs;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import com.queencastle.dao.model.bbs.BBSComment;

public interface BBSCommentMapper {
	BBSComment getById(@Param("id") String id);

	int insert(BBSComment bBSComment);

	Integer getBBSCommentCountByParams(@Param("map") Map<String, Object> map);

	List<BBSComment> getBBSCommentByParams(@Param("page") Pageable pageable, @Param("map") Map<String, Object> map);

}
