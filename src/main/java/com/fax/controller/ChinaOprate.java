package com.fax.controller;

import com.fax.entity.NocvData;
import com.fax.service.IndexService;
import com.fax.vo.DataView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/china")
public class ChinaOprate {
    @Autowired
    private IndexService indexService;

    @RequestMapping("/deletelie")
    @ResponseBody
    public DataView deleteLie(Integer id){
        indexService.removeById(id);
        DataView dataView = new DataView();
        dataView.setCode(200);
        return dataView;
    }

    @RequestMapping("/doaddorupdate")
    @ResponseBody
    public DataView addNum(NocvData nocvData){
        boolean save = indexService.saveOrUpdate(nocvData);
        DataView dataView = new DataView();
        if (save){
            dataView.setCode(200);
            dataView.setMsg("数据插入成功！");
        }else {
            dataView.setCode(100);
            dataView.setMsg("数据插入失败！");
        }
        return dataView;
    }


}
