package com.example.os_be.controller;

import com.example.os_be.domain.Response;
import com.example.os_be.domain.directory_management.UFD;
import com.example.os_be.service.IMFDManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class MFDController {

    @Autowired
    private IMFDManagementService mFDManagementImpl;

    /**
     * 通过用户id查找该用户的文件目录
     * @param MFDId
     * @return
     */
    @GetMapping("/mfd/search/{MFDId}")
    public Response<ArrayList<UFD>> searchUserByUserName(@PathVariable("MFDId") Integer MFDId) {
        Response<ArrayList<UFD>> response = new Response<>();
        ArrayList<UFD> result = mFDManagementImpl.searchFileByMFDId(MFDId);
        String message = result == null||result.size()==0 ? "该用户还没有文件" : "搜索成功";
        response.setCode(0).setMessage(message).setData(result);
        return response;
    }

    /**
     * 创建空文件目录项
     * @param ufd
     * @return  返回文件目录项id
     */
    @PostMapping("/mfd/create")
    public Response<Integer> createFileDir(@RequestBody UFD ufd) {
        Response<Integer> response = new Response<>();
        // TODO:做条件判断
        int result = mFDManagementImpl.createFileDir(ufd);
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
     * 修改文件名
     */
    @GetMapping("/mfd/modify_file_name")
    public Response<Integer> modifyFileName(@RequestParam("ufdId") int ufdId, @RequestParam("fileName") String fileName) {
        Response<Integer> response = new Response<>();
        if (fileName.isEmpty()) {
            response.setCode(-1).setMessage("文件名不能为空");
            return response;
        }
        int result = mFDManagementImpl.modifyFileDir(ufdId, fileName);
        int code = -1;
        String message = "修改失败";
        if (result != 0) {
            code = 0;
            message = "修改成功";
        }
        response.setCode(code).setMessage(message).setData(result);
        return response;
    }

    @GetMapping("/mfd/open_file")
    public Response<Integer> openFile(@RequestParam("ufdId") int ufdId) {
        Response<Integer> response = new Response<>();
        // TODO：条件查找
        int result = mFDManagementImpl.openFile(ufdId);
        int code = -1;
        String message = "打开失败";
        if (result != 0) {
            code = 0;
            message = "打开成功";
        }
        response.setCode(code).setMessage(message).setData(result);
        return response;
    }

    @GetMapping("/mfd/close_file")
    public Response<Integer> closeFile(@RequestParam("ufdId") int ufdId) {
        Response<Integer> response = new Response<>();
        // TODO：条件查找
        int result = mFDManagementImpl.closeFile(ufdId);
        int code = -1;
        String message = "关闭失败";
        if (result != 0) {
            code = 0;
            message = "关闭成功";
        }
        response.setCode(code).setMessage(message).setData(result);
        return response;
    }

}
