package com.fax.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fax.entity.XueYuan;
import com.fax.service.XueYuanService;
import com.fax.vo.DataView;
import com.fax.vo.XueYuanVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class XueYuanController {
    @Autowired
    private XueYuanService xueYuanService;
    @RequestMapping("/toxueyuan")
    public String toXueYuan(){
        return "xueyuan/xueyuan";
    }
    @RequestMapping("/xueyuan/querydata")
    @ResponseBody
    public DataView queryData(XueYuanVo xueYuanVo){
        IPage<XueYuan> page = new Page<>(xueYuanVo.getSize(),xueYuanVo.getLimit());
        QueryWrapper<XueYuan> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(xueYuanVo.getName()),"name",xueYuanVo.getName());
        xueYuanService.page(page,queryWrapper);
        return new DataView(page.getTotal(),page.getRecords());
    }
    @RequestMapping("/xueyuan/deletenews")
    @ResponseBody
    public DataView deleteData(Integer id){
        boolean remove = xueYuanService.removeById(id);
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

    @RequestMapping("/xueyuan/addnews")
    @ResponseBody
    public DataView addData(XueYuan xueYuan){
        boolean save = xueYuanService.save(xueYuan);
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

    @RequestMapping("/xueyuan/updatenews")
    @ResponseBody
    public DataView updateData(XueYuan xueYuan){
        boolean update = xueYuanService.updateById(xueYuan);
        DataView dataView = new DataView();
        if (update){
            dataView.setCode(200);
            dataView.setMsg("数据更新1成功！");
            return dataView;
        }
        dataView.setCode(100);
        dataView.setMsg("数据更新失败！");
        return dataView;
    }


}
