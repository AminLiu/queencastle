package com.queencastle.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.queencastle.service.interf.UserService;
import com.queencastle.service.sessions.PermissionContext;
import com.queencastle.service.vo.MenuVO;

@Controller
public class SysMainController {
    @Autowired
    private UserService userService;


    @RequestMapping("/sysmain")
    public String sysmain(Model model) {
        List<MenuVO> menuList = new ArrayList<MenuVO>();
        menuList.addAll(userService.getMenuVosByUser(PermissionContext.getUser()));
        model.addAttribute("menuList", menuList);
        return "sysmain";
    }



}
