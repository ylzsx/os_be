package com.example.os_be.service;

import com.example.os_be.domain.disk_management.DiskSecondIndex;
import com.example.os_be.pojo.DiskBitmapResponse;
import com.example.os_be.pojo.DiskResponse;
import com.example.os_be.pojo.FileRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface IDiskManagementService {

    // 根据文件ufdId和页码进行分页查找
    @Transactional(rollbackFor = Exception.class)
    ArrayList<DiskResponse> searchFile(int ufdId, int page);

    // 创建文件
    @Transactional(rollbackFor = Exception.class)
    int createFile(FileRequest request);

    // 删除文件
    @Transactional(rollbackFor = Exception.class)
    int deleteFile(int ufdId);

    // 展示位示图
    ArrayList<DiskBitmapResponse> showBitmap();
}
