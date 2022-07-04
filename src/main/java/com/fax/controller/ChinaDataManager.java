package com.fax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChinaDataManager {

    //跳转到疫情数据增删改查的界面
    @RequestMapping("/chinadatamanager")
    public String toChinaDataManager(){
        return "admin/chinadatamanage";
    }

    //完成分页的功能
    
}
