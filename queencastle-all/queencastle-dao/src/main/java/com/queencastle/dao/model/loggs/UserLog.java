package com.queencastle.dao.model.loggs;

import com.queencastle.dao.model.BaseModel;

/**
 * 系统用户日志
 * 
 * @author YuDongwei
 *
 */
public class UserLog extends BaseModel {

    private static final long serialVersionUID = 511764616723940615L;
    private String userId;
    /** 日志类型 */
    private LogType logType;
    /** 内容描述 */
    private String content;
    /** 被关联表的编号 */
    private String objectId;

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

    public LogType getLogType() {
        return logType;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

}
