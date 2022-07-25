package com.fax.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fax.entity.Menu;
import com.fax.entity.Role;
import com.fax.service.MenuService;
import com.fax.service.RoleService;
import com.fax.util.TreeNode;
import com.fax.vo.DataView;
import com.fax.vo.RoleVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator;
import org.omg.CORBA.DATA_CONVERSION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.management.QueryEval;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @RequestMapping("/torole")
    public String toRole(){
        return "role/role";
    }

    /**
     *查询带有分页的角色名称与角色备注
     * @return
     */
    @RequestMapping("/role/loadAllRole")
    @ResponseBody
    public DataView loadAlRole(RoleVo roleVo) {
        IPage<Role> page = new Page<>(roleVo.getSize(),roleVo.getLimit());
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank("name"),"name",roleVo.getName());
        queryWrapper.like(StringUtils.isNotBlank("remark"),"remark",roleVo.getRemark());
        roleService.page(page,queryWrapper);
        return new DataView(page.getTotal(),page.getRecords());
    }

    /**
     * 新增或修改角色角色
     * @return
     */
    @RequestMapping("/role/addRole")
    @ResponseBody
    public DataView addRole(Role role){
        roleService.save(role);
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("新增角色成功！");
        return dataView;
    }

    /**
     * 删除角色
     * @param role
     * @return
     */
    @RequestMapping("/role/deleteRole")
    public DataView deleteRole(Role role){
        roleService.removeById(role.getId());
        DataView dataView = new DataView();
        dataView.setMsg("删除角色成功！");
        dataView.setCode(200);
        return dataView;
    }

    @RequestMapping("/role/updateRole")
    @ResponseBody
    public DataView updateRole(Role role){
        roleService.saveOrUpdate(role);
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("角色修改成功!");
        return dataView;
    }

    @RequestMapping("/role/initPermissionByRoleId")
    @ResponseBody
    public DataView initMenu(Integer roleId){
        //1：先自定义一个queryMidByRid的查询方法来查询roleId对应的mid
       List<Integer> currentMid =  roleService.queryMidByRid(roleId);
       QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
       List<Menu> list = null;
       //2：如果查到有mid，就将所有的对应菜单信息放到list中
       if (currentMid.size() > 0){
           queryWrapper.in("id",currentMid);
           list = menuService.list(queryWrapper);
       }

       //3：创建一个树的小工具
        ArrayList<TreeNode> nodes = new ArrayList<>();
        List<Menu> allmenuList = menuService.list();
        //4：创建两个for循环，主要目的就是判断check是否选中，并是否展开
        for (Menu allmenu : allmenuList) {
            String check = "1";
                for (Menu currentMenu : list) {
                    if (currentMenu.getId().equals(allmenu.getId())) {
                        check = "0";
                        break;
                    }
                }

            Boolean spread = (allmenu.getOpen()==null || allmenu.getOpen() == 1) ?  true :false;
            nodes.add(new TreeNode(allmenu.getId(),allmenu.getPid(),allmenu.getTitle(),spread,check));
        }
        //5：将树结点nodes返回到前端
        return new DataView(nodes);
    }

    @RequestMapping("/role/saveRolePermission")
    @ResponseBody
    public DataView saveData(Integer rid, Integer[] mids){
        DataView dataView = new DataView();
        //1：先删除数据库中存的rid与mid
        roleService.deleteById(rid);
        //2：将前端传来的数据存入数据库
        if (mids != null){
            for (Integer mid : mids) {
                roleService.saveByRid(rid,mid);
            }
            dataView.setMsg("数据插入成功！");
            dataView.setCode(200);
            return dataView;
        }
        dataView.setMsg("数据插入失败！");
        dataView.setCode(100);
        return dataView;
    }

}
