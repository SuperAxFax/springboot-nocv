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

import java.util.ArrayList;
import java.util.List;

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
}
