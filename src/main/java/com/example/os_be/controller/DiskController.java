package com.example.os_be.controller;

import com.example.os_be.domain.Constants;
import com.example.os_be.domain.Response;
import com.example.os_be.pojo.DiskBitmapResponse;
import com.example.os_be.pojo.DiskResponse;
import com.example.os_be.pojo.FileRequest;
import com.example.os_be.service.IDiskManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class DiskController {

    @Autowired
    private IDiskManagementService diskManagementImpl;

    /**
     * 查找文件内容
     * @param ufdId
     * @param page  分页查找页码(从0开始)
     * @return
     */
    @GetMapping("/disk/search_file")
    public Response<ArrayList<DiskResponse>> searchFile(@RequestParam("ufdId") int ufdId, @RequestParam("page") int page) {
        Response<ArrayList<DiskResponse>> response = new Response<>();
        // TODO:做条件判断
        ArrayList<DiskResponse> result = diskManagementImpl.searchFile(ufdId, page);
        int code = -2;
        String message = "查找失败";
        if (result == null|| result.size() == 0) {
            message = "已到最后";
        } else {
            code = 0;
            message = "查找成功";
        }
        response.setCode(code).setMessage(message).setData(result);
        return response;
    }

    /**
     * 创建文件
     * @param request
     * @return
     */
    @PostMapping("/disk/create_file")
    public Response<Integer> createFile(@RequestBody FileRequest request) {
        Response<Integer> response = new Response<>();
        // TODO:做条件判断
        if (request.getLength() < 0 && !request.getContent().isEmpty()) {
            request.setLength((int) Math.ceil(request.getContent().length()/(double) Constants.STRING_LENGTH_PER_DISK));
        }
        int result = diskManagementImpl.createFile(request);
        String message = "";
        int code = -1;
        if (result == 0) {
            message = "创建失败";
        } else if (result == -2) {
            code = -2;
            message = "磁盘不够用";
        } else {
            code = 0;
            message = "创建成功";
        }
        response.setCode(code).setMessage(message).setData(result);
        return response;
    }

    /**
     * 删除文件
     * @param ufdId
     * @return
     */
    @GetMapping("/disk/delete_file")
    public Response<Integer> deleteFile(@RequestParam("ufdId") int ufdId) {
        Response<Integer> response = new Response<>();

        int result = diskManagementImpl.deleteFile(ufdId);
        String message = "";
        int code = -1;
        if (result == 0) {
            message = "删除失败";
        } else {
            code = 0;
            message = "删除成功";
        }
        response.setCode(code).setMessage(message).setData(result);
        return response;
    }

    /**
     * 显示位示图
     * @return
     */
    @GetMapping("/disk/show_bitmap")
    public Response<ArrayList<DiskBitmapResponse>> showBitmap() {
        Response<ArrayList<DiskBitmapResponse>> response = new Response<>();

        ArrayList<DiskBitmapResponse> result = diskManagementImpl.showBitmap();
        String message = "";
        int code = -1;
        if (result == null || result.size() == 0) {
            message = "查找失败";
        } else {
            code = 0;
            message = "查找成功";
        }
        response.setCode(code).setMessage(message).setData(result);
        return response;
    }
}
