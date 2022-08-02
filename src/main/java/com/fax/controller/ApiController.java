package com.fax.controller;

import com.fax.entity.News;
import com.fax.entity.NocvApiData;
import com.fax.service.ApiService;
import com.fax.service.NewsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.Date;
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
        //引入reids思路
        //1: 首先创建一个连接
        //2: 获取缓存中的内容并判断是否为空
        //3：不为空就直接将数据打包返回前端，为空就进行数据库查询，查询之后再返回前端
        //4：在数据更新处进行清空缓存的操作
        Jedis jedis = new Jedis("localhost");
        if (jedis != null) {
            String confirm = jedis.get("confirm");
            String input = jedis.get("input");
            String heal = jedis.get("heal");
            String dead = jedis.get("dead");
            String datetime = jedis.get("datetime");
            if (StringUtils.isNotBlank(confirm)
                    && StringUtils.isNotBlank(input)
                    && StringUtils.isNotBlank(heal)
                    && StringUtils.isNotBlank(dead)
                    && StringUtils.isNotBlank(datetime)
            ) {
                NocvApiData nocvApiData = new NocvApiData();
                nocvApiData.setConfirm(Integer.parseInt(confirm));
                nocvApiData.setInput(Integer.parseInt(input));
                nocvApiData.setHeal(Integer.parseInt(heal));
                nocvApiData.setDead(Integer.parseInt(dead));
                nocvApiData.setDatatime(new Date());
                System.out.println("已查询出数据" + nocvApiData);
                model.addAttribute("apiData", nocvApiData);
            } else {
                //1：查询数据库最后一列的数据
                Integer id = apiService.getMaxId();
                NocvApiData apiData = apiService.getById(id);
                model.addAttribute("apiData", apiData);
                System.out.println(apiData);
                jedis.set("confirm",String.valueOf(apiData.getConfirm()));
                jedis.set("input",String.valueOf(apiData.getInput()));
                jedis.set("heal",String.valueOf(apiData.getHeal()));
                jedis.set("dead",String.valueOf(apiData.getDead()));
                jedis.set("datetime",String.valueOf(datetime));
            }
        }
        //2：加入时间线内容
        List<News> newslist = newsService.querylimit5();
        model.addAttribute("newslist",newslist);
        return "china";
    }

}
