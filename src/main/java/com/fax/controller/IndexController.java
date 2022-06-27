package com.fax.controller;

import com.fax.entity.NocvData;
import com.fax.service.IndexService;
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

    @RequestMapping("/query")
    @ResponseBody//作用是将List序列化成为一个json实体
    public List<NocvData> queryDta(){
        List<NocvData> list = indexService.list();
        System.out.println("list的值为"+list);
        return list;
    }
}
