package com.fax.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fax.entity.User;
import com.fax.service.UserService;
import com.fax.vo.DataView;
import com.fax.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/touser")
    public String toUser(){
        return "user/user";
    }
    @RequestMapping("/tochangepassword")
    public String toChangepassword(){
        return "user/changepassword";
    }

    @RequestMapping("/userinfo")
    public String toUserInfo(){
        return "user/userInfo";
    }

    /**
     * 初始化用户表中的数据
     * @param userVo
     * @return
     */
    @RequestMapping("/user/loadAllUser")
    @ResponseBody
    public DataView loadAllUser(UserVo userVo){
        //补充：进行连表查询得到学院名和班级名
        //思路：搭建学院与班级的mybatis-plus框架，使用usre表中的对应学院id与班级id，到对应的mybatis-plus中进行查询，
        //将查询得到的结果放置到前端

        IPage<User> page = new Page<>(userVo.getSize(),userVo.getLimit());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank("usrename"),"username",userVo.getUsername());
        queryWrapper.like(StringUtils.isNotBlank("phone"),"phone",userVo.getPhone());
        userService.page(page,queryWrapper);
        return new DataView(page.getTotal(),page.getRecords());
    }


}
