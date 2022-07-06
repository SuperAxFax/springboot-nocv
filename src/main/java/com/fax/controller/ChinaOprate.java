package com.fax.controller;

import com.fax.entity.NocvData;
import com.fax.service.IndexService;
import com.fax.vo.DataView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/china")
public class ChinaOprate {
    @Autowired
    private IndexService indexService;

    @RequestMapping("/deletelie")
    @ResponseBody
    public DataView deleteLie(Integer id){
        indexService.removeById(id);
        DataView dataView = new DataView();
        dataView.setCode(200);
        return dataView;
    }

    /**
     * savaOrUpdate的原理是：如果前端传来的nocvdate中有id表示的就是修改，如果没有id表示的就是新增；
     * @param nocvData
     * @return
     */
    @RequestMapping("/doaddorupdate")
    @ResponseBody
    public DataView doAddOrUpdate(NocvData nocvData){
        boolean save = indexService.saveOrUpdate(nocvData);
        DataView dataView = new DataView();
        if (save){
            dataView.setCode(200);
            dataView.setMsg("数据插入成功！");
        }else {
            dataView.setCode(100);
            dataView.setMsg("数据插入失败！");
        }
        System.out.println(dataView);
        return dataView;
    }
}
