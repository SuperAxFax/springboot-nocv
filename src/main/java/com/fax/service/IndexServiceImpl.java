package com.fax.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fax.dao.IndexMapper;
import com.fax.entity.NocvData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexServiceImpl extends ServiceImpl<IndexMapper, NocvData> implements IndexService {

    @Autowired
    private IndexMapper indexMapper;
    @Override
    public List<NocvData> querylimit34() {
        return indexMapper.querylimit34();
    }
}
