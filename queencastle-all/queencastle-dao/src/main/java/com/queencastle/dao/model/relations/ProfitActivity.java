package com.queencastle.dao.model.relations;

import com.queencastle.dao.model.BaseModel;

/**
 * 返利活动
 * 
 * @author YuDongwei
 *
 */
public class ProfitActivity extends BaseModel {

    private static final long serialVersionUID = -1473885194863516491L;
    /** 策略编号 */
    private String strategyId;

    public String getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(String strategyId) {
        this.strategyId = strategyId;
    }


}
