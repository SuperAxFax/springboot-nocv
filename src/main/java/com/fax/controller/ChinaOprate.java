package com.fax.controller;

import com.fax.service.IndexService;
import com.fax.vo.DataView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
