package com.fax.controller;

import com.fax.entity.News;
import com.fax.entity.NocvApiData;
import com.fax.service.ApiService;
import com.fax.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ApiController {

    @Autowired
    private ApiService apiService;
    @Autowired
    private NewsService newsService;
    @RequestMapping("/in")
    public String toindex(Model model){
        //1：查询数据库最后一列的数据
        Integer id = apiService.getMaxId();
        NocvApiData apiData = apiService.getById(id);
        model.addAttribute("apiData",apiData);
        System.out.println(apiData);
        return "index";
    }

    @RequestMapping("/tochina")
    public String tochina(Model model){
        //1：查询数据库最后一列的数据
        Integer id = apiService.getMaxId();
        NocvApiData apiData = apiService.getById(id);
        model.addAttribute("apiData",apiData);
        System.out.println(apiData);

        //2：加入时间线内容
        List<News> newslist = newsService.querylimit5();
        model.addAttribute("newslist",newslist);
        return "china";
    }

}
