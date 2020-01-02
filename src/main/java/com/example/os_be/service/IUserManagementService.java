package com.example.os_be.service;

import com.example.os_be.domain.user_management.User;

public interface IUserManagementService {

    // 根据用户名查询是否存在该用户
    int searchUserByUserName(String userName);

    // 创建用户
    int creteUser(User user);

    // 创建用户
    int login(User user);
}
