package com.queencastle.dao.model.weixin;

import com.queencastle.dao.model.BaseModel;
import com.queencastle.dao.model.PropertyDict;

/**
 * 用户会员意向
 * 
 * @author YuDongwei
 *
 */
public class UserIntention extends BaseModel {

    private static final long serialVersionUID = -2593617356288802947L;
    private String userId;
    /** 从事时间 */
    private PropertyDict timeWork;
    /** 团队人数 */
    private PropertyDict teamCnt;
    /** 销售产品 */
    private String saleInfo;
    /** 年销售额 */
    private PropertyDict saleAmount;
    /** 事业规划 */
    private String planInfo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public PropertyDict getTimeWork() {
        return timeWork;
    }

    public void setTimeWork(PropertyDict timeWork) {
        this.timeWork = timeWork;
    }

    public PropertyDict getTeamCnt() {
        return teamCnt;
    }

    public void setTeamCnt(PropertyDict teamCnt) {
        this.teamCnt = teamCnt;
    }

    public String getSaleInfo() {
        return saleInfo;
    }

    public void setSaleInfo(String saleInfo) {
        this.saleInfo = saleInfo;
    }

    public PropertyDict getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(PropertyDict saleAmount) {
        this.saleAmount = saleAmount;
    }

    public String getPlanInfo() {
        return planInfo;
    }

    public void setPlanInfo(String planInfo) {
        this.planInfo = planInfo;
    }



}
