package com.queencastle.dao.mapper.goods;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import com.queencastle.dao.model.goods.DemandSupplyInfo;

public interface DemandSupplyInfoMapper {
	int insert(DemandSupplyInfo dsInfo);

	List<DemandSupplyInfo> getByParams(@Param("paramMap") Map<String, Object> paramMap);

	DemandSupplyInfo getById(@Param("id") String id);

	Integer getDemandSupplysCountByParams(@Param("map") Map<String, Object> map);

	List<DemandSupplyInfo> getDemandSupplysByParams(@Param("page") Pageable pageable,
			@Param("map") Map<String, Object> map);

	List<DemandSupplyInfo> getAllDemandSupplyInfo();

	Boolean updateCheck(@Param("result") int check, @Param("id") String id);

	List<DemandSupplyInfo> getAllByType(@Param("type") String type);

	void addCnt(@Param("id") String infoId);

	void minusCnt(@Param("id") String infoId);

	List<DemandSupplyInfo> getAllByUserId(@Param("userId") String userId);

	List<DemandSupplyInfo> getByQueryParams(@Param("queryMap") Map<String, Object> queryMap);

	List<DemandSupplyInfo> getThreeByUserId(@Param("userId") String userId, @Param("infoId") String infoId);
}
