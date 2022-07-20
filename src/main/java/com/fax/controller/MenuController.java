package com.fax.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fax.entity.Menu;
import com.fax.service.MenuService;
import com.fax.util.TreeNode;
import com.fax.vo.DataView;
import com.fax.vo.MenuDataVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.util.resources.cldr.vai.CalendarData_vai_Vaii_LR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MenuController {

     @Autowired
     private MenuService menuService;
    /**
     * 跳转到menu界面
     * @return
     */
    @RequestMapping("/menu")
    public String toMenu(){
        return "menu/menu";
    }

    /**
     * 对menu界面的分页查询
     * @return
     */
    @RequestMapping("/querymenupage")
    @ResponseBody
    public DataView query(MenuDataVo menuDataVo){
        IPage<Menu> page =  new Page<>(menuDataVo.getSize(),menuDataVo.getLimit());
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNoneBlank(menuDataVo.getTitle()),"title",menuDataVo.getTitle());
        queryWrapper.orderByAsc("ordernum");
        menuService.page(page,queryWrapper);
        return new DataView(page.getTotal(),page.getRecords());
    }

    /**
     * 初始化下拉菜单
     * @return
     */
    @RequestMapping("/menu/loadMenuManagerLeftTreeJson")
    @ResponseBody
    public DataView loadtree(){
        List<Menu> list = menuService.list();
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Menu menu : list) {
            Boolean open = menu.getOpen() == 1 ? true : false;
            treeNodes.add(new TreeNode(menu.getId(),menu.getPid(),menu.getTitle(),open));
        }
        DataView dataView = new DataView(treeNodes);
        return dataView;
    }

    /**
     * 初始化排序码，让排序码变为最大值加一
     * @return
     */
    @RequestMapping("/menu/loadMenuMaxOrderNum")
    @ResponseBody
    public Map<String,Object> initnumber(){
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("ordernum");
        IPage<Menu> page = new Page<>(1,1);
        List<Menu> records = menuService.page(page, queryWrapper).getRecords();
        Map map = new HashMap<>();
        map.put("value",records.get(0).getOrdernum()+1);
        return map;
    }

    /**
     * 新增菜单
     * @param menu
     * @return
     */
    @RequestMapping("/menu/addMenu")
    @ResponseBody
    public DataView addMenu(Menu menu){
        menuService.save(menu);
        DataView dataView = new DataView();
        if (!(menu==null)){
            dataView.setMsg("菜单新增成功！");
            return dataView;
        }
        dataView.setMsg("菜单新增失败！");
        return dataView;
    }

    /**
     * 更新菜单
     * @return
     */
    @RequestMapping("/menu/updateMenu")
    @ResponseBody
    public DataView updateMenu(Menu menu){
        boolean update = menuService.updateById(menu);
        DataView dataView = new DataView();
        if (update){
            dataView.setMsg("菜单更新成功");
            dataView.setCode(200);
            return dataView;
        }
        dataView.setMsg("菜单更新失败");
        dataView.setCode(100);
        return dataView;
    }

    /**
     * 判断当前菜单是否是父菜单
     * @return
     */
    @RequestMapping("/menu/checkMenuHasChildrenNode")
    @ResponseBody
    public Map<String,Object> checkMenuHasChildrenNode(Menu menu){
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", menu.getId());
        List<Menu> list = menuService.list(queryWrapper);
        Map map = new HashMap<>();
        if (list.size()>0){//说明该菜单是父菜单，不可以删除
            map.put("value",true);
        }else {
            map.put("value",false);
        }
        return map;
    }

    /**
     * 真正的删除操作
     * @return
     */
    @RequestMapping("/menu/deleteMenu")
    @ResponseBody
    public DataView deleteMenu(Menu menu){
        boolean remove = menuService.removeById(menu.getId());
        DataView dataView = new DataView();
        if (remove){
            dataView.setMsg("菜单删除成功！");
            dataView.setCode(200);
            return dataView;
        }
        dataView.setMsg("菜单删除失败！");
        dataView.setCode(100);
        return dataView;
    }


}
