package com.fax.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fax.entity.NocvData;
import com.fax.entity.NocvLine;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 从数据库获取中国疫情图的数据
 */
public interface IndexMapper extends BaseMapper<NocvData> {
    @Select("select * from nocv_line order by createtime limit 7")
    List<NocvLine> querydataDao();

    //查询nocv_data表中后34位的数据
    @Select("select * from nocv_data order by id desc limit 34")
    List<NocvData> querylimit34();
}

