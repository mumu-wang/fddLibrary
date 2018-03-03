package com.fdd.lms.service;

import com.fdd.lms.Model.User;
import com.fdd.lms.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lin.wang
 * @date 2018-03-02 21:33.
 */

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public User getUserById(String id){
        return userDAO.selectUserById(id);
    }

    public void addUser(User user){
        userDAO.insertUser(user);
    }
}
