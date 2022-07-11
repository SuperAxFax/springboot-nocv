package com.fax.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fax.entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where username = #{username} and password = #{password}")
    User check(String username, String password);
}
