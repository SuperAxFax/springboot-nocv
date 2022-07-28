package com.fax.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fax.dao.RoleMapper;
import com.fax.entity.Role;

import java.util.List;

public interface RoleService extends IService<Role> {

    List<Integer> queryMidByRid(Integer roleId);

    void deleteById(Integer rid);


    void saveByRid(Integer rid, Integer mid);

    List<Integer> queryCurrentMaps(Integer id);

    void deleteByUid(Integer id);

    void saveByUid(Integer uid, Integer rid);
}
