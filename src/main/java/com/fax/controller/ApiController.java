package com.fax.controller;

import com.fax.entity.NocvApiData;
import com.fax.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiController {

    @Autowired
    private ApiService apiService;
    @RequestMapping("/")
    public String toindex(Model model){
        //1：查询数据库最后一列的数据
        Integer id = apiService.getMaxId();
        NocvApiData apiData = apiService.getById(id);
        model.addAttribute("apiData",apiData);
        System.out.println(apiData);
        return "index";
    }


}
