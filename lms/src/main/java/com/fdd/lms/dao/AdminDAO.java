package com.fdd.lms.dao;

import com.fdd.lms.Model.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Lin.wang
 * @date 2018-03-10 10:25.
 */

@Mapper
public interface AdminDAO {
    String TABLE_NAME = " admininfo ";
    String SELECT_FIELDS = " admin_id , admin_pw ";

    @Select({"select", SELECT_FIELDS ," from ",TABLE_NAME, " where admin_id = #{adminId}"})
    public Admin selectAdminById(String adminId);

}
