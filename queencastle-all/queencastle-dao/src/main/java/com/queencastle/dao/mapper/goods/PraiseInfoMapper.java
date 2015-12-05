package com.queencastle.dao.mapper.goods;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.queencastle.dao.model.goods.PraiseInfo;
import com.queencastle.dao.model.goods.PraiseType;

public interface PraiseInfoMapper {
	int insert(PraiseInfo info);

	int getCnt(@Param("infoId") String infoId, @Param("userId") String userId);

	void update(@Param("infoId") String infoId, @Param("userId") String userId, @Param("type") PraiseType type);

	PraiseType getTypeByUserId(@Param("userId") String userId, @Param("infoId") String infoId);

	List<String> getUserIdByInfoId(@Param("infoId") String infoId);
}
