package com.fax.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fax.entity.NocvApiData;
import org.apache.ibatis.annotations.Select;

public interface ApiMapper extends BaseMapper<NocvApiData> {
    @Select("select max(id) from nocv_apidata")
    Integer getMaxId();
}
