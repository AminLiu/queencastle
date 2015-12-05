package com.queencastle.web.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 系统异常处理页面
 * 
 * @author YuDongwei
 *
 */
@Controller
public class ExceptionHandlerController {
    private static Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @RequestMapping("/globalError")
    public ModelAndView handler(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        logger.error("request.getRequestURI():" + request.getRequestURI());
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        logger.error("exception:" + throwable);
        mv.addObject("timestamp", new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(new Date()));
        mv.setViewName("exception");
        return mv;
    }
}
