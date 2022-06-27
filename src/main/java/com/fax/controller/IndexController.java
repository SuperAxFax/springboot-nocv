package com.fax.controller;

import com.fax.entity.NocvData;
import com.fax.entity.NocvPie;
import com.fax.service.IndexService;
import com.fax.service.PieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.List;
@Controller
public class IndexController {
    @Autowired
    private IndexService  indexService;

    @Autowired
    private PieService pieService;

    @RequestMapping("/query")
    @ResponseBody//作用是将List序列化成为一个json实体
    public List<NocvData> queryDta(){
        List<NocvData> list = indexService.list();
        System.out.println("list的值为"+list);
        return list;
    }

    //查询饼状图中的数据
    @RequestMapping("/querypie")
    @ResponseBody
    public List<NocvPie> queryPie(){
        List<NocvPie> list = pieService.list();
        return list;
    }

    //跳转到pie界面
    @RequestMapping("/topie")
    public String toPie(){
        return "pie";
    }
}
