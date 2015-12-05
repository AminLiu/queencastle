package com.queencastle.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShopIndexController {

    /**
     * 商城主页
     * 
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
