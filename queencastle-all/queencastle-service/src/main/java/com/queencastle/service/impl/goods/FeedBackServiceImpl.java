package com.queencastle.service.impl.goods;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.mapper.goods.FeedBackMapper;
import com.queencastle.dao.model.goods.FeedBack;
import com.queencastle.dao.model.relations.UserMember;
import com.queencastle.service.interf.goods.FeedBackService;

@Service
public class FeedBackServiceImpl implements FeedBackService {
    @Autowired
    private FeedBackMapper feedBackMapper;

    @Override
    public int insert(FeedBack feedBack) {
        return feedBackMapper.insert(feedBack);
    }

    @Override
    public List<FeedBack> getByDemondInfoId(String infoId) {
        if (StringUtils.isNoneBlank(infoId)) {
            return feedBackMapper.getByDemondInfoId(infoId);
        }
        return null;
    }

    @Override
    public List<FeedBack> getBySupplyUserId(String userId) {
        if (StringUtils.isNoneBlank(userId)) {
            return feedBackMapper.getBySupplyUserId(userId);
        }
        return null;
    }

	@Override
	public PageInfo<FeedBack> getFeedbackByParams(int page, int rows, Map<String, Object> queryParam) {
		PageInfo<FeedBack> pageInfo = new PageInfo<FeedBack>();
		pageInfo.setPage(page);
		Integer count = feedBackMapper.getFeedBackCountByParams(queryParam);
		if (count == null || count == 0) {
			pageInfo.setTotal(0);
			pageInfo.setRows(new ArrayList<FeedBack>());
			return pageInfo;
		}
		pageInfo.setTotal(count);
		page = (page <= 1) ? 1 : page;
		Pageable pageable = new PageRequest(page - 1, rows);
		List<FeedBack> list = feedBackMapper.getFeedbackByParams(pageable, queryParam);
		pageInfo.setRows(list);
		return pageInfo;
		
	}

}
