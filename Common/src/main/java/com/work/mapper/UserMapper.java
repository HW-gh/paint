package com.work.mapper;

import com.work.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select * from user where name = #{username}")
    public User getUserByName(@Param("username")String username);
}
