package com.fdd.lms.controller;

import com.fdd.lms.Model.Admin;
import com.fdd.lms.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Lin.wang
 * @date 2018-03-10 10:17.
 */
@Controller
@RequestMapping(value = "/adminLogin")
public class AdminLogin {
    @Autowired
    AdminService adminService;

    /*
     *功能：管理员登录
     */
    @RequestMapping(method = RequestMethod.GET)
    public String adminLoginForm() {
        return "adminLoginForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String adminLogin(@ModelAttribute Admin admin, ModelMap map, HttpServletResponse response) {
        try {
            Map<String, Object> mp = adminService.loginAdmin(admin);
            if (mp.containsKey("ticket")) {
                //TBD 【密码未加密，cookie先写个固定值】
                Cookie cookie = new Cookie("ticket", mp.get("ticket").toString());
                cookie.setPath("/");
                cookie.setMaxAge(3600 * 24);
                response.addCookie(cookie);
                return "redirect:/admin";

            } else {
                map.addAttribute("errinfo", "用户名或密码错误！");
                return "errorInfo";
            }

        } catch (Exception e) {
            map.addAttribute("errinfo", e.getMessage());
            return "errorInfo";
        }

    }
}
