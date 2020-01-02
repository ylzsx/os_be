package com.example.os_be.service.impl;

import com.example.os_be.domain.user_management.User;
import com.example.os_be.dao.IUserDao;
import com.example.os_be.service.IUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagementImpl implements IUserManagementService {

    @Autowired
    IUserDao userDao;

    @Override
    public int searchUserByUserName(String userName) {
        return userDao.searchUserByUserName(userName);
    }

    @Override
    public int creteUser(User user) {
        userDao.creteUser(user);
        return user.getId();
    }

    @Override
    public int login(User user) {
        return userDao.login(user);
    }
}
