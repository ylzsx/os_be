package com.example.os_be.controller;

import com.example.os_be.domain.Response;
import com.example.os_be.domain.user_management.User;
import com.example.os_be.service.IUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private IUserManagementService userManagementImpl;

    /**
     * 根据用户名查找是否有该用户
     * @param userName
     * @return
     */
    @GetMapping("/user/search")
    public Response<Integer> searchUserByUserName(@RequestParam("userName") String userName) {
        Response<Integer> response = new Response<>();
        if (userName.isEmpty()) {
            response.setCode(-1).setMessage("用户名不能为空");
            return response;
        }
        int result = userManagementImpl.searchUserByUserName(userName);
        String message = result == 0 ? "没有该用户" : "存在该用户";
        response.setCode(0).setMessage(message).setData(result);
        return response;
    }

    /**
     * 创建用户
     * @param user
     * @return  返回该用户的id
     */
    @PostMapping("/user/create_user")
    public Response<Integer> createUser(@RequestBody User user) {
        Response<Integer> response = new Response<>();
        if (user.getUserName().isEmpty() || user.getPassword().isEmpty()) {
            response.setCode(-1).setMessage("用户名或密码不能为空");
            return response;
        }
        int result = userManagementImpl.creteUser(user);
        int code = -1;
        String message = "创建失败";
        if (result != 0) {
            code = 0;
            message = "创建成功";
        }
        response.setCode(code).setMessage(message).setData(result);
        return response;
    }

    /**
     * 登录
     * @param user
     * @return 返回该用户的id
     */
    @PostMapping("user/login")
    public Response<Integer> login(@RequestBody User user) {
        Response<Integer> response = new Response<>();
        if (user.getUserName().isEmpty() || user.getPassword().isEmpty()) {
            response.setCode(-1).setMessage("用户名或密码不能为空");
            return response;
        }
        int result = userManagementImpl.login(user);
        int code = -1;
        String message = "登录失败";
        if (result != 0) {
            code = 0;
            message = "登录成功";
        }
        response.setCode(code).setMessage(message).setData(result);
        return response;
    }

}
