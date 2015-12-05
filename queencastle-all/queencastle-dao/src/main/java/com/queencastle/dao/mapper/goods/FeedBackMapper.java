package com.queencastle.dao.mapper.goods;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import com.queencastle.dao.model.goods.FeedBack;

public interface FeedBackMapper {
    int insert(FeedBack feedBack);

    List<FeedBack> getByDemondInfoId(@Param("infoId") String userId);

    List<FeedBack> getBySupplyUserId(@Param("userId") String userId);

	Integer getFeedBackCountByParams(@Param("queryParam") Map<String, Object>  queryParam);

	List<FeedBack> getFeedbackByParams(@Param("page") Pageable pageable,
            @Param("queryParam") Map<String, Object> queryParam);
}
