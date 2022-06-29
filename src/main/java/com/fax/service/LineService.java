package com.fax.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fax.dao.LineMapper;
import com.fax.entity.NocvLine;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LineService extends IService<NocvLine> {
    List<NocvLine> querydata();
}
