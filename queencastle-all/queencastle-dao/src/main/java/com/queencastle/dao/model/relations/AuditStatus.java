package com.queencastle.dao.model.relations;

/**
 * 审核状态
 * 
 * @author YuDongwei
 *
 */
public enum AuditStatus {
    /** 待审核 */
    undone,
    /** 审核通过 */
    success,
    /** 审核不通过 */
    fail;
    public static AuditStatus getByName(String name) {
        switch (name) {
            case "undone":
                return undone;
            case "success":
                return success;
            case "fail":
                return success;
            default:
                return undone;
        }
    }
}
