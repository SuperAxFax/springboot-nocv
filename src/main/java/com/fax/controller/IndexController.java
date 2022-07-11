package com.fax.controller;

import com.fax.entity.NocvData;
import com.fax.entity.NocvGlobal;
import com.fax.entity.NocvLine;
import com.fax.entity.NocvPie;
import com.fax.service.GlobalService;
import com.fax.service.IndexService;
import com.fax.service.LineService;
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

    @Autowired
    private LineService lineService;

    @Autowired
    private GlobalService globalService;

    @RequestMapping("/query")
    @ResponseBody//作用是将List序列化成为一个json实体
    public List<NocvData> queryDta(){
        List<NocvData> list = indexService.querylimit34();
        return list;
    }

    //查询饼状图中的数据
    @RequestMapping("/querypie")
    @ResponseBody
    public List<NocvPie> queryPie(){
        List<NocvPie> list = pieService.list();
        return list;
    }

    //查询数据库中的折线图数据并提供给前端
    @RequestMapping("/queryline")
    @ResponseBody
    public Map<String,List<Object>> queryLine(){
        //调用service层的方法，然后在service层的LineService接口创建->LineServiceImpl来实现
        //实现类中需要调dao层中的查询方法，然后再继续创建新的函数即可。
        List<NocvLine> querydata = lineService.querydata();
        List<Integer> confirm = new ArrayList<>();
        List<Integer> seperate = new ArrayList<>();
        List<Integer> cure = new ArrayList<>();
        List<Integer> dead = new ArrayList<>();
        List<Integer> similar = new ArrayList<>();
        for (NocvLine line : querydata) {
            confirm.add(line.getConfirm());
            seperate.add(line.getSeperate());
            cure.add(line.getCure());
            dead.add(line.getDead());
            similar.add(line.getSimilar());
        }

        Map map = new HashMap();
        map.put("confirm",confirm);
        map.put("seperate",seperate);
        map.put("cure",cure);
        map.put("dead",dead);
        map.put("similar",similar);

        return map;
    }

    //查询柱状图
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

    //查询全球疫情图
    @RequestMapping("/queryglobal")
    @ResponseBody
    public List<NocvGlobal> queryGlobal(){
        List<NocvGlobal> list = globalService.list();
        return list;
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

    //跳转到line页面
    @RequestMapping("/toline")
    public String toLine(){
        return "line";
    }

    //跳转到global页面
    @RequestMapping("/toglobal")
    public String toGlobal(){
        return "global";
    }
}
