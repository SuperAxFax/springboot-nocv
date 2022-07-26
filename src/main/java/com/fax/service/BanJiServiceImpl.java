package com.fax.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fax.dao.BanJiMapper;
import com.fax.entity.BanJi;
import org.springframework.stereotype.Service;

@Service
public class BanJiServiceImpl extends ServiceImpl<BanJiMapper, BanJi> implements BanJiService {
}
