package com.fax.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fax.dao.PieMapper;
import com.fax.entity.NocvPie;
import org.springframework.stereotype.Service;

@Service
public class PieServiceImpl extends ServiceImpl<PieMapper, NocvPie> implements PieService {
}
