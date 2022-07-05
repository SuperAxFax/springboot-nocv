package com.fax.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fax.entity.NocvData;
import com.fax.service.IndexService;
import com.fax.vo.DataView;
import com.fax.vo.NocvDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;

@Controller
public class ChinaDataManager {

    @Autowired
    private IndexService indexService;
    //跳转到疫情数据增删改查的界面
    @RequestMapping("/chinadatamanager")
    public String toChinaDataManager(){
        return "admin/chinadatamanage";
    }

    //进行分页查询，并将数据返回到前台
    @RequestMapping("/querypage")
    @ResponseBody
    public DataView queryPage(NocvDataVo nocvDataVo){
        //1：进行分页操作
        IPage<NocvData> page = new Page<>(nocvDataVo.getSize(), nocvDataVo.getLimit());
        //2：查询条件的书写
        QueryWrapper<NocvData> wrapper = new QueryWrapper<>();
        wrapper.like(!(nocvDataVo.getName() == null),"name",nocvDataVo.getName());
        //3：编写查询规则
        wrapper.orderByDesc("value");

        //4：查询数据库
        indexService.page(page,wrapper);

        //5：返回数据
        DataView dataView = new DataView(page.getTotal(), page.getRecords());
        return dataView;
    }

}
