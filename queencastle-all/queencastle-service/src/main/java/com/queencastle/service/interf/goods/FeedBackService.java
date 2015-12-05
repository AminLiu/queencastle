package com.queencastle.service.interf.goods;

import java.util.List;
import java.util.Map;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.goods.FeedBack;

public interface FeedBackService {
    int insert(FeedBack feedBack);

    /**
     * 根据需求发布人查出对应的反馈列表
     */
    List<FeedBack> getByDemondInfoId(String infoId);

    /***
     * 查询某个人一共发了多少评价
     * 
     * @param userId
     * @return
     */
    List<FeedBack> getBySupplyUserId(String userId);

	PageInfo<FeedBack> getFeedbackByParams(int page, int rows, Map<String, Object> queryParam);
}
