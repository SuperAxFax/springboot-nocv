package com.fax.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fax.entity.Vaccine;
import com.fax.service.VaccineService;
import com.fax.vo.DataView;
import com.fax.vo.VaccineVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VaccineController {
    @Autowired
    private VaccineService vaccineService;

    @RequestMapping("/tovaccine")
    public String toVaccine(){
        return "vaccine/vaccine";
    }

    @RequestMapping("/queryvaccinepage")
    @ResponseBody
    public DataView queryData(VaccineVo vaccineVo){
        IPage<Vaccine> page = new Page<>(vaccineVo.getSize(),vaccineVo.getLimit());
        QueryWrapper<Vaccine> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(vaccineVo.getName()),"name",vaccineVo.getName());
        vaccineService.page(page,queryWrapper);
        return new DataView(page.getTotal(),page.getRecords());
    }

    @RequestMapping("/vaccine/addMenu")
    @ResponseBody
    public DataView addVAccine(Vaccine vaccine){
        boolean save = vaccineService.save(vaccine);
        DataView dataView = new DataView();
        if (save){
            dataView.setCode(200);
            dataView.setMsg("数据插入成功！");
            return dataView;
        }
        dataView.setCode(100);
        dataView.setMsg("数据插入失败！");
        return dataView;
    }

    @RequestMapping("/vaccine/updateMenu")
    @ResponseBody
    public DataView updateVaccine(Vaccine vaccine){
        boolean update = vaccineService.updateById(vaccine);
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

    @RequestMapping("/vaccine/deleteMenu")
    @ResponseBody
    public DataView deleteVAccine(Integer id){
        boolean remove = vaccineService.removeById(id);
        DataView dataView = new DataView();
        if (remove){
            dataView.setCode(200);
            dataView.setMsg("数据更新成功！");
            return dataView;
        }
        dataView.setCode(100);
        dataView.setMsg("数据更新失败！");
        return dataView;
    }


}
