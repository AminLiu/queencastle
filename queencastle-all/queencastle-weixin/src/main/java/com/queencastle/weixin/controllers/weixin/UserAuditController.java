package com.queencastle.weixin.controllers.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.queencastle.dao.model.User;
import com.queencastle.dao.model.loggs.LogType;
import com.queencastle.dao.model.loggs.UserLog;
import com.queencastle.dao.model.relations.AuditStatus;
import com.queencastle.dao.model.relations.UserAudit;
import com.queencastle.dao.model.weixin.UserIntention;
import com.queencastle.service.interf.loggs.UserLogService;
import com.queencastle.service.interf.relations.UserAuditService;
import com.queencastle.service.interf.weixin.UserIntentionService;
import com.queencastle.service.sessions.PermissionContext;

@Controller
@RequestMapping("/audit")
public class UserAuditController {

    @Autowired
    private UserAuditService userAuditService;
    @Autowired
    private UserLogService userLogService;
    @Autowired
    private UserIntentionService userIntentionService;

    @Transactional
    @RequestMapping("/applyAudit")
    public String applyAudit(@ModelAttribute("userIntention") UserIntention userIntention) {
        User user = PermissionContext.getUser();
        userIntention.setUserId(user.getId());
        userIntentionService.insert(userIntention);
        UserAudit userAudit = new UserAudit();
        userAudit.setApplyUser(user);
        userAudit.setAuditUser(new User());
        userAudit.setAuditStatus(AuditStatus.undone);
        userAudit.setReason("");
        userAuditService.insert(userAudit);
        UserLog userLog = new UserLog();
        userLog.setContent("提交审核");
        userLog.setLogType(LogType.audit);
        userLog.setObjectId(userAudit.getId());
        userLog.setUserId(user.getId());
        return "redirect:/undone";
    }
}
