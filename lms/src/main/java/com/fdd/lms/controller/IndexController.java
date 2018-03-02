package com.fdd.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Lin.wang
 * @date 2018-03-02 21:46.
 */

@Controller
@RequestMapping(value = {"/","/index"})
public class IndexController {

    @RequestMapping(method = RequestMethod.GET)
    public String visitIndex(){
        return "index";
    }
}
