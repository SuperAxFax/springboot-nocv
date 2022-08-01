package com.fax.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fax.entity.HeSuan;
import com.fax.service.HeSuanService;
import com.fax.vo.DataView;
import com.fax.vo.HeSuanVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HeSuanController {
    @Autowired
    private HeSuanService heSuanService;

    @RequestMapping("/tohesuan")
    public String toHeSuan(){
        return "hesuan/hesuan";
    }

    @RequestMapping("/queryhesuanpage")
    @ResponseBody
    public DataView queryData(HeSuanVo heSuanVo){
        IPage<HeSuan> page = new Page<>(heSuanVo.getSize(),heSuanVo.getLimit());
        QueryWrapper<HeSuan> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(heSuanVo.getName()),"name",heSuanVo.getName());
        heSuanService.page(page,queryWrapper);
        return new DataView(page.getTotal(),page.getRecords());
    }

    @RequestMapping("/hesuan/addMenu")
    @ResponseBody
    public DataView addHeSuan(HeSuan heSuan){
        boolean save = heSuanService.save(heSuan);
        DataView dataView = new DataView();
        if (save){
            dataView.setMsg("数据新增成功！");
            dataView.setCode(200);
            return dataView;
        }
        dataView.setMsg("数据新增失败！");
        dataView.setCode(100);
        return dataView;
    }

    @RequestMapping("/hesuan/updateMenu")
    @ResponseBody
    public DataView updateHeSuan(HeSuan heSuan){
        boolean update = heSuanService.updateById(heSuan);
        DataView dataView = new DataView();
        if (update){
            dataView.setMsg("数据修改成功！");
            dataView.setCode(200);
            return dataView;
        }
        dataView.setMsg("数据修改失败！");
        dataView.setCode(100);
        return dataView;
    }

    @RequestMapping("/hesuan/deleteMenu")
    @ResponseBody
    public DataView deleteHeSuan(Integer id){
        boolean remove = heSuanService.removeById(id);
        DataView dataView = new DataView();
        if (remove){
            dataView.setMsg("数据删除成功！");
            dataView.setCode(200);
            return dataView;
        }
        dataView.setMsg("数据删除失败！");
        dataView.setCode(100);
        return dataView;
    }
}

