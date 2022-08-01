package com.fax.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fax.entity.BanJi;
import com.fax.service.BanJiService;
import com.fax.vo.BanJiVo;
import com.fax.vo.DataView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BanJiController {
    @Autowired
    private BanJiService banJiService;
    @RequestMapping("/toclass")
    public String toBanJi(){
        return "banji/banji";
    }
    @RequestMapping("/banji/querydata")
    @ResponseBody
    public DataView queryData(BanJiVo banJiVo){
        IPage<BanJi> page = new Page<>(banJiVo.getSize(),banJiVo.getLimit());
        QueryWrapper<BanJi> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(banJiVo.getName()),"name",banJiVo.getName());
        banJiService.page(page,queryWrapper);
        return new DataView(page.getTotal(),page.getRecords());
    }
    @RequestMapping("/banji/deletenews")
    @ResponseBody
    public DataView deleteData(Integer id){
        boolean remove = banJiService.removeById(id);
        DataView dataView = new DataView();
        if (remove){
            dataView.setCode(200);
            dataView.setMsg("数据删除成功！");
            return dataView;
        }
        dataView.setCode(100);
        dataView.setMsg("数据删除失败！");
        return dataView;
    }

    @RequestMapping("/banji/addnews")
    @ResponseBody
    public DataView addData(BanJi banJi){
        boolean save = banJiService.save(banJi);
        DataView dataView = new DataView();
        if (save){
            dataView.setCode(200);
            dataView.setMsg("数据添加成功！");
            return dataView;
        }
        dataView.setCode(100);
        dataView.setMsg("数据添加失败！");
        return dataView;
    }

    @RequestMapping("/banji/updatenews")
    @ResponseBody
    public DataView updateData(BanJi banJi){
        boolean update = banJiService.updateById(banJi);
        DataView dataView = new DataView();
        if (update){
            dataView.setCode(200);
            dataView.setMsg("数据更新成功！");
            return dataView;
        }
        dataView.setCode(100);
        dataView.setMsg("数据更新失败！");
        return dataView;
    }
}
