package com.fax.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fax.dao.IndexMapper;
import com.fax.dao.LineMapper;
import com.fax.entity.NocvLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineServiceImpl extends ServiceImpl<LineMapper, NocvLine> implements LineService {

    @Autowired
    private IndexMapper indexMapper;
    @Override
    public List<NocvLine> querydata() {
        List<NocvLine> list = indexMapper.querydataDao();
        return list;
    }
}
