package com.fax.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fax.dao.ApiMapper;
import com.fax.entity.NocvApiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiServiceImpl extends ServiceImpl<ApiMapper,NocvApiData> implements ApiService {
    @Autowired
    private ApiMapper apiMapper;
    @Override
    public Integer getMaxId() {
        return apiMapper.getMaxId();
    }
}
