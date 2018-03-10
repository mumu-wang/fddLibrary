package com.fdd.lms.controller;

import com.fdd.lms.Model.User;
import com.fdd.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Lin.wang
 * @date 2018-03-02 23:16.
 */
@Controller
@RequestMapping(value = "/admin/user")
public class UserController {
    @Autowired
    UserService userService;

    /*
     *功能：新增用户
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String newUserForm() {
        return "userForm";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String newUser(@ModelAttribute User user, ModelMap map) {

        if (userService.getUserById(user.getUserId()) != null) {
            map.addAttribute("errinfo", "改用户已存在，请勿重复注册！");
            return "errorinfo";
        }

        if (user.getUserName() == null) {
            user.setUserName(user.getUserId());
        }
        if (user.getUserPw() == null) {
            user.setUserPw(user.getUserId());
        }
        userService.addUser(user);
        return "redirect:/admin";
    }

}
