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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //查询数据库中的数据并提供给前端
    @RequestMapping("/querybar")
    @ResponseBody
    public Map<String,List<Object>> queryBar(){
        //查询后端数据
        List<NocvData> list = indexService.list();
        //获取数据中的name值
        List<String> name = new ArrayList<>();
        for (NocvData data : list) {
            name.add(data.getName());
        }
        //获取数据中的value值
        List<Integer> value = new ArrayList<>();
        for (NocvData data : list) {
            value.add(data.getValue());
        }
        //将name和value加入到map中并返回
        Map map = new HashMap();
        map.put("cityname",name);
        map.put("cityvalue",value);

        System.out.println(name);
        System.out.println(value);
        return map;
    }
    //跳转到pie界面
    @RequestMapping("/topie")
    public String toPie(){
        return "pie";
    }

    //跳转到bar界面
    @RequestMapping("/tobar")
    public String toBar(){
        return "bar";
    }
}
