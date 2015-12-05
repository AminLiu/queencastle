package com.queencastle.dao.model.relations;

import com.queencastle.dao.model.BaseModel;
import com.queencastle.dao.model.User;

/**
 * 会员申请审核
 * 
 * @author YuDongwei
 *
 */
public class UserAudit extends BaseModel {

    private static final long serialVersionUID = -7660638830814189963L;
    /** 审核状态 */
    private AuditStatus auditStatus;
    /** 申请人 */
    private User applyUser;
    /** 审核人 */
    private User auditUser;
    /** 审核不通过的理由 */
    private String reason;

    public AuditStatus getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public User getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(User applyUser) {
        this.applyUser = applyUser;
    }

    public User getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(User auditUser) {
        this.auditUser = auditUser;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }



}
