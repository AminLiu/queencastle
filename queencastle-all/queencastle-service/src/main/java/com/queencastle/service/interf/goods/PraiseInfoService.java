package com.queencastle.service.interf.goods;

import java.util.List;

import com.queencastle.dao.model.goods.PraiseInfo;
import com.queencastle.dao.model.goods.PraiseType;

public interface PraiseInfoService {
	int insert(PraiseInfo info);

	/**
	 * 根据用户编号，事件编号 查询是否已经关注/取消关注
	 * 
	 * @return
	 */
	int getCnt(String infoId, String userId);

	void update(String infoId, String userId, PraiseType addpraise);

	PraiseType getTypeByUserId(String userId, String infoId);

	List<String> getUserIdByInfoId(String infoId);
}
