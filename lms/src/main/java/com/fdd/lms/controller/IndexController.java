package com.fdd.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Lin.wang
 * @date 2018-03-02 21:46.
 */

@Controller
@RequestMapping(value = {"/", "/index"})
public class IndexController {
    /*
     *功能：首页,暂时做了cookie清理工作
     */
    @RequestMapping(method = RequestMethod.GET)
    public String visitIndex(HttpServletRequest req, HttpServletResponse response) {
        Cookie[] cookies = req.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("ticket")) {
                    cookie.setValue("");
                    response.addCookie(cookie);
                }
            }
        }
        return "index";
    }
}
