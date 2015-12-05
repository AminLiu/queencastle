package com.queencastle.service.interf.goods;

import java.util.List;
import java.util.Map;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.model.goods.DemandSupplyInfo;

public interface DemandSupplyInfoService {

    List<DemandSupplyInfo> getAllDemandSupplyInfo();

    int insert(DemandSupplyInfo dsInfo);

    List<DemandSupplyInfo> getByParams(Map<String, Object> paramMap);

    DemandSupplyInfo getById(String id);

    PageInfo<DemandSupplyInfo> getDemandSupplysByParams(int page, int rows, Map<String, Object> map);

    boolean updateCheck(int result, String id);

    List<DemandSupplyInfo> getAllByType(String type);

    /***
     * @deprecated <p>
     *             此方法适用于在同一个位置上点击关注和取消关注两个状态来回切换
     *             </p>
     *             点赞 加一，取消点赞减一
     * 
     */
    int addPaiseCnt(int score, String infoId, String userId);

    /**
     * 单纯的关注
     * 
     * @param score
     * @param infoId
     * @param userId
     * @return
     */
    int addAttention(int score, String infoId, String userId);

    List<DemandSupplyInfo> getAllByUserId(String UserId);

    List<DemandSupplyInfo> getByQueryParams(Map<String, Object> queryMap);

    List<DemandSupplyInfo> getThreeByUserId(String userId, String infoId);
}
