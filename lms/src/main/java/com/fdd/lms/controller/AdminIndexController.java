package com.fdd.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Lin.wang
 * @date 2018-03-10 11:36.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminIndexController {

    /*
    *功能：管理员首页
    */
    @RequestMapping(method = RequestMethod.GET)
    public String intoAdminIndex(){
        return "adminIndex";
    }
}
