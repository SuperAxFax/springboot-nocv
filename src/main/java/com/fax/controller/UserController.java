package com.fax.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fax.entity.BanJi;
import com.fax.entity.User;
import com.fax.entity.XueYuan;
import com.fax.service.BanJiService;
import com.fax.service.UserService;
import com.fax.service.XueYuanService;
import com.fax.vo.DataView;
import com.fax.vo.UserVo;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.soap.SOAPBinding;
import javax.xml.crypto.Data;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BanJiService banJiService;
    @Autowired
    private XueYuanService xueYuanService;
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
        IPage<User> page = new Page<>(userVo.getSize(),userVo.getLimit());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(userVo.getUsername()),"username",userVo.getUsername());
        //queryWrapper.like(StringUtils.isNotBlank(userVo.getPhone()),"phone",userVo.getPhone());
        IPage<User> iPage = userService.page(page, queryWrapper);

        //补充：进行连表查询得到学院名和班级名
        //思路：搭建学院与班级的mybatis-plus框架，使用usre表中的对应学院id与班级id，到对应的mybatis-plus中进行查询，
        //将查询得到的结果放置到前端
        for (User user : iPage.getRecords()) {
            BanJi banJi = banJiService.getById(user.getBanjiId());
            user.setBanjiName(banJi.getName());
            XueYuan xueYuan = xueYuanService.getById(user.getXueyuanId());
            user.setXueyuanName(xueYuan.getName());
        }
        DataView dataView = new DataView(iPage.getTotal(), iPage.getRecords());
        return dataView;
    }

    /**
     * 查询班级下拉列表的数据并返回
     * @return
     */
    @RequestMapping("/user/listallbanji")
    @ResponseBody
    public List<BanJi> listAllBanji(){
        List<BanJi> list = banJiService.list();
        return list;
    }

    /**
     * 查询学院下拉列表的数据并返回
     * @return
     */
    @RequestMapping("/user/listallxueyuan")
    @ResponseBody
    public List<XueYuan> listAllXueYuan(){
        List<XueYuan> list = xueYuanService.list();
        return list;
    }

    //新增数据
    @RequestMapping("/user/addUser")
    @ResponseBody
    public DataView addUser(User user){
        boolean save = userService.save(user);
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("数据新增成功！");
        return dataView;
    }

    /**
     * 更新用户数据
     * @param user
     * @return
     */
    @RequestMapping("/user/updateUser")
    @ResponseBody
    public DataView updateUser(User user){
        boolean b = userService.updateById(user);
        DataView dataView = new DataView();
        dataView.setMsg("数据更新成功！");
        dataView.setCode(200);
        return dataView;
    }

    /**
     * 删除用户数据
     * @param id
     * @return
     */
    @RequestMapping("user/deleteUser/{id}")
    @ResponseBody
    public DataView deleteUser(@PathVariable("id") Integer id){
        boolean remove = userService.removeById(id);
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("删除用户成功！");
        return dataView;
    }

    /**
     * 重置密码
     * @param id
     * @param
     * @return
     */
    @RequestMapping("/user/resetPwd/{id}")
    @ResponseBody
    public DataView updatePassword(@PathVariable("id") Integer id){
        User user = new User();//使用User user = userService.getById(id);  user.setPassword("123456");没有成功的原因是，这样写不会调用mybatis-plus中的sql语句对数据库进行更新。
        user.setId(id);
        user.setPassword("123456");
        userService.updateById(user);
        DataView dataView = new DataView();
        dataView.setMsg("密码修改成功！");
        dataView.setCode(200);
        return dataView;
    }

}
