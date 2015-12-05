package com.queencastle.dao.model.goods;

import com.queencastle.dao.model.BaseModel;

/**
 * 反馈信息
 * 
 * @author YuDongwei
 *
 */
public class FeedBack extends BaseModel {

    private static final long serialVersionUID = -4221219083598331813L;
    /** 供需消息编号 */
    private String infoId;
    /** 反馈用户编号 */
    private String userId;
    /** 反馈内容 */
    private String content;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }


}
