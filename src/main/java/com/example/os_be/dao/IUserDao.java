package com.example.os_be.dao;

import com.example.os_be.domain.user_management.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao {

    // 根据用户名查询是否存在该用户
    int searchUserByUserName(String userName);

    // 创建用户
    int creteUser(User user);

    // 用户登录
    int login(User user);
}
