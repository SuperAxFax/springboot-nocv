package com.fax.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fax.dao.GlobalMapper;
import com.fax.entity.NocvGlobal;
import org.springframework.stereotype.Service;

@Service
public class GlobalServiceImpl extends ServiceImpl<GlobalMapper, NocvGlobal> implements GlobalService {
}
