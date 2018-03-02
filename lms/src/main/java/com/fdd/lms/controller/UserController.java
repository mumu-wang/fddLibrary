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
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String newUserForm(){
        return "userForm";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String newUser(@ModelAttribute User user, ModelMap map){
        //TBD 重复注册，项目所有创建地方都应该注意
        if(user.getUserName() == null){
            user.setUserName(user.getUserId());
        }
        if(user.getUserPw() == null){
            user.setUserPw(user.getUserId());
        }
        userService.addUser(user);
        return "redirect:/";
    }

}
