package com.fax.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fax.entity.News;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NewsMapper extends BaseMapper<News> {
    //查询数据库中最近五条疫情信息
    @Select("select * from news order by createtime desc limit 5")
    List<News> querylimit5();
}
