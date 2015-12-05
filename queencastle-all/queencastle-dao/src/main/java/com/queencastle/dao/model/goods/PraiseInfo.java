package com.queencastle.dao.model.goods;

import com.queencastle.dao.model.BaseModel;

/**
 * 点赞(关注)相关
 * 
 * @author YuDongwei
 *
 */
public class PraiseInfo extends BaseModel {

    private static final long serialVersionUID = -1019567116759819709L;
    /** 对应事件编号 */
    private String infoId;
    /** 点赞用户编号 */
    private String userId;
    /** 加减分类型 */
    private PraiseType type;

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public PraiseType getType() {
        return type;
    }

    public void setType(PraiseType type) {
        this.type = type;
    }



}
