package com.example.os_be.dao;

import com.example.os_be.domain.directory_management.UFD;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.ArrayList;

@Repository
public interface IMFDDao {

    // 根据用户id查找某一用户的所有文件
    ArrayList<UFD> searchFileByMFDId(int MFDId);

    // 创建文件目录块，返回创建成功后的主键
    int createFileDir(UFD ufd);

    // 修改目录项,返回影响的条数
    int modifyFileDir(UFD ufd);

    // 删除文件目录项,返回影响的条数
    int deleteFileDir(int ufdId);

    // 打开文件 (1代表打开，0代表关闭)
    int openFile(int ufdId);

    // 关闭文件
    int closeFile(int ufdId);
}
