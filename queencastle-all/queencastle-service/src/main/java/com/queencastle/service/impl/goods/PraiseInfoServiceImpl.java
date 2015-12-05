package com.queencastle.service.impl.goods;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.queencastle.dao.mapper.goods.PraiseInfoMapper;
import com.queencastle.dao.model.goods.PraiseInfo;
import com.queencastle.dao.model.goods.PraiseType;
import com.queencastle.service.interf.goods.PraiseInfoService;

@Service
public class PraiseInfoServiceImpl implements PraiseInfoService {
	@Autowired
	private PraiseInfoMapper praiseInfoMapper;

	@Override
	public int insert(PraiseInfo info) {
		return praiseInfoMapper.insert(info);
	}

	@Override
	public int getCnt(String infoId, String userId) {
		if (StringUtils.isNoneBlank(infoId) && StringUtils.isNoneBlank(userId)) {
			return praiseInfoMapper.getCnt(infoId, userId);
		}
		return 0;
	}

	@Override
	public void update(String infoId, String userId, PraiseType type) {

		praiseInfoMapper.update(infoId, userId, type);
	}

	@Override
	public PraiseType getTypeByUserId(String userId, String infoId) {
		if (StringUtils.isNoneBlank(userId)) {
			return praiseInfoMapper.getTypeByUserId(userId, infoId);
		}

		return null;
	}

	@Override
	public List<String> getUserIdByInfoId(String infoId) {

		return praiseInfoMapper.getUserIdByInfoId(infoId);
	}

}
