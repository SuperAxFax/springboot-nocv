package com.fax.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fax.entity.News;
import com.fax.service.NewsService;
import com.fax.vo.DataView;
import com.fax.vo.NewsVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xddf.usermodel.LineEndWidth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class NewsController {
    @Autowired
    private NewsService newsService;
    /**
     * 跳转到news界面
     * @return
     */
    @RequestMapping("/news/tonews")
     public String toNews(){
          return "/news/news";
     }

     @RequestMapping("/news/querydata")
     @ResponseBody
     public DataView listAllData(NewsVo newsVo){
         IPage<News> page = new Page<>(newsVo.getSize(),newsVo.getLimit());
         QueryWrapper<News> queryWrapper = new QueryWrapper<>();
         queryWrapper.like(StringUtils.isNotBlank(newsVo.getTitle()),"title",newsVo.getTitle());
         newsService.page(page,queryWrapper);
         return new DataView(page.getTotal(),page.getRecords());
     }

     @RequestMapping("/news/deletenews")
     @ResponseBody
     public DataView deleteNews(Integer id){
        newsService.removeById(id);
        DataView dataView = new DataView();
        dataView.setMsg("数据删除成功！");
        dataView.setCode(200);
        return dataView;
     }

     @RequestMapping("/news/addnews")
     @ResponseBody
     public DataView addOrUpdate(News news){
         boolean saveOrUpdate = newsService.save(news);
         DataView dataView = new DataView();
         if (saveOrUpdate){
         dataView.setMsg("数据新增成功！");
         dataView.setCode(200);
         return dataView;
         }
         dataView.setMsg("数据新增失败！");
         dataView.setCode(200);
         return dataView;
     }


    @RequestMapping("/news/updatenews")
    @ResponseBody
    public DataView Update(News news){
        boolean Update = newsService.updateById(news);
        DataView dataView = new DataView();
        if (Update){
            dataView.setMsg("数据修改成功！");
            dataView.setCode(200);
            return dataView;
        }
        dataView.setMsg("数据修改失败！");
        dataView.setCode(200);
        return dataView;
    }


}
