package com.fax.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fax.dao.MenuMapper;
import com.fax.entity.Menu;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
}
