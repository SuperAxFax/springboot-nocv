package com.fax.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fax.dao.RoleMapper;
import com.fax.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;


    @Override
    public List<Integer> queryMidByRid(Integer roleId) {
        return roleMapper.queryMidByRid(roleId);
    }

    @Override
    public void deleteById(Integer rid) {
        roleMapper.deleteByRid(rid);
    }

    @Override
    public void saveByRid(Integer rid, Integer mid) {
        roleMapper.saveByRid(rid,mid);
    }

    @Override
    public List<Integer> queryCurrentMaps(Integer id) {
        return roleMapper.queryCurrentMaps(id);
    }


}
