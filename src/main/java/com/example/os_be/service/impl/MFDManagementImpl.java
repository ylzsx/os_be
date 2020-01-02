package com.example.os_be.service.impl;

import com.example.os_be.domain.directory_management.UFD;
import com.example.os_be.dao.IMFDDao;
import com.example.os_be.service.IMFDManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MFDManagementImpl implements IMFDManagementService {

    @Autowired
    IMFDDao mFDDao;

    @Override
    public ArrayList<UFD> searchFileByMFDId(int MFDId) {
        return mFDDao.searchFileByMFDId(MFDId);
    }

    @Override
    public int createFileDir(UFD ufd) {
        // TODO:查找是否已有该文件
        mFDDao.createFileDir(ufd);
        return ufd.getUfdId();
    }

    @Override
    public int modifyFileDir(int ufdId, String fileName) {
        // TODO:查看该文件是否被打开
        UFD ufd = new UFD();
        ufd.setUfdId(ufdId);
        ufd.setFileName(fileName);
        return mFDDao.modifyFileDir(ufd);
    }

    @Override
    public int openFile(int ufdId) {
        return mFDDao.openFile(ufdId);
    }

    @Override
    public int closeFile(int ufdId) {
        return mFDDao.closeFile(ufdId);
    }
}
