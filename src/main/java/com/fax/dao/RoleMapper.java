package com.fax.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fax.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    @Select("select mid from role_menu where rid = #{roleId}")
    List<Integer> queryMidByRid(Integer roleId);

    @Delete("delete from role_menu where rid = #{rid}")
    void deleteByRid(Integer rid);

    @Insert("insert into role_menu(rid,mid) values (#{rid},#{mid})")
    void saveByRid(Integer rid, Integer mid);
}
