package com.fax.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fax.entity.NocvData;

import java.util.List;

public interface IndexService extends IService<NocvData> {

    List<NocvData> querylimit34();
}
