package com.example.os_be.service;

import com.example.os_be.domain.directory_management.UFD;

import java.util.ArrayList;

public interface IMFDManagementService {

    // 根据用户id查找某一用户的所有文件
    ArrayList<UFD> searchFileByMFDId(int MFDId);

    // 创建文件目录块
    int createFileDir(UFD ufd);

    // 修改文件目录名
    int modifyFileDir(int ufdId, String fileName);

    // 打开文件
    int openFile(int ufdId);

    // 关闭文件
    int closeFile(int ufdId);
}
