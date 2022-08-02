package com.fax.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fax.dao.VaccineMapper;
import com.fax.entity.Vaccine;
import org.springframework.stereotype.Service;

@Service
public class VaccineServiceImpl extends ServiceImpl<VaccineMapper, Vaccine> implements VaccineService {
}
