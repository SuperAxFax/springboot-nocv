package com.fax.controller;

import com.fax.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/china")
public class ChinaOprate {
    @Autowired
    private IndexService indexService;

    @RequestMapping("/deletelie")
    public void deleteLie(Integer id){
        indexService.removeById(id);
    }
}
