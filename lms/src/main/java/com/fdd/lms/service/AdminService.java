package com.fdd.lms.service;

import com.fdd.lms.Model.Admin;
import com.fdd.lms.dao.AdminDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lin.wang
 * @date 2018-03-10 10:35.
 */
@Service
public class AdminService {
    @Autowired
    private AdminDAO adminDAO;

    public Admin getAdminById(String adminId){
        return adminDAO.selectAdminById(adminId);
    }

    public Map<String,Object> loginAdmin(Admin admin){
        Map<String,Object> map = new HashMap<String, Object>();
        if(admin == null){
            map.put("error","用户名或密码错误！");
            return map;
        }else if(adminDAO.selectAdminById(admin.getAdminId()) == null){
            map.put("error","用户名或密码错误！");
            return map;
        }
        else if(!adminDAO.selectAdminById(admin.getAdminId()).getAdminPw().equals(admin.getAdminPw())){
            map.put("error","用户名或密码错误！");
            return map;
        }
        String ticket = "lin.wang10225213";//TBD　待后续完善^_^
        map.put("ticket",ticket);

        return map;
    }

}
