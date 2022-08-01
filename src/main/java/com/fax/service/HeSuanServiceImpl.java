package com.fax.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fax.dao.HeSuanMapper;
import com.fax.entity.HeSuan;
import org.springframework.stereotype.Service;

@Service
public class HeSuanServiceImpl extends ServiceImpl<HeSuanMapper, HeSuan> implements  HeSuanService{
}
