package com.fax.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fax.entity.User;

public interface UserService extends IService<User> {
    User check(String username, String password);
}
