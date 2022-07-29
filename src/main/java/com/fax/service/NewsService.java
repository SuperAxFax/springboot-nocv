package com.fax.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fax.entity.News;

import java.util.List;

public interface NewsService extends IService<News> {
    List<News> querylimit5();
}
