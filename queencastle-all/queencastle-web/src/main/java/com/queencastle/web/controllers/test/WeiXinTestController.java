package com.queencastle.web.controllers.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.queencastle.dao.model.User;
import com.queencastle.service.interf.UserDetailInfoService;
import com.queencastle.service.interf.UserService;
import com.queencastle.service.interf.relations.UserAuditService;

@Controller
@RequestMapping("/test")
public class WeiXinTestController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailInfoService userDetailInfoService;

    @Autowired
    private UserAuditService userAuditService;

    @ResponseBody
    @RequestMapping("/testInit")
    public int testInit() {
        List<User> users = userService.getAllUserIds();
        for (User user : users) {
            userDetailInfoService.updateOrInsertForTest(user.getId());
            userAuditService.updateOrInsert(user);
        }
        return 0;
    }

}
