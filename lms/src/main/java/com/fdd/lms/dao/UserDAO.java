package com.fdd.lms.dao;

import com.fdd.lms.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Lin.wang
 * @date 2018-03-02 21:32.
 */

@Mapper
public interface UserDAO {

    String TABLE_NAME = "userinfo";
    String INSET_FIELDS = " user_id, user_name, user_pw ";
    String SELECT_FIELDS = " user_id, user_name, user_pw ";

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where user_id=#{id}"})
    User selectUserById(String id);

    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
            ") values (#{userId},#{userName},#{userPw})"})
    int insertUser(User user);
}
