package com.queencastle.dao.mapper.bbs;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import com.queencastle.dao.model.bbs.BBSBoard;

public interface BBSBoardMapper {

	BBSBoard getById(@Param("id") String id);
	
	int insert(BBSBoard bBSBoard);

	Integer getBBSBoardCountByParams(@Param("map") Map<String, Object> map);

	List<BBSBoard> getBBSBoardByParams(@Param("page") Pageable pageable, @Param("map") Map<String, Object> map);

}
