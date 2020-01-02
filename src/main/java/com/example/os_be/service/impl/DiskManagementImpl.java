package com.example.os_be.service.impl;

import com.example.os_be.dao.IDiskDao;
import com.example.os_be.dao.IMFDDao;
import com.example.os_be.domain.Constants;
import com.example.os_be.domain.disk_management.DiskFirstIndex;
import com.example.os_be.domain.disk_management.DiskSecondIndex;
import com.example.os_be.domain.disk_management.SwapTable;
import com.example.os_be.pojo.DiskBitmapResponse;
import com.example.os_be.pojo.DiskResponse;
import com.example.os_be.pojo.FileRequest;
import com.example.os_be.service.IDiskManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class DiskManagementImpl implements IDiskManagementService {

    @Autowired
    private IDiskDao diskDao;

    @Autowired
    private IMFDDao mFDDao;

    /**
     * 1. 查找对换区
     * 2. 对换区没有查用户区
     * 3. 把用户区数据全部交给对换区
     * 4. 把此次数据从对换区交给内存
     * @param ufdId
     * @param page
     * @return
     */
    @Override
    public ArrayList<DiskResponse> searchFile(int ufdId, int page) {
        ArrayList<DiskResponse> result = new ArrayList<>();
        int start = page * Constants.BLOCK_SIZE_PER_TIME + 1;
        int end = (page + 1) * Constants.BLOCK_SIZE_PER_TIME;

        // 1. 查找对换区
        ArrayList<SwapTable> swapTables = diskDao.searchSwapByUFDId(ufdId, start, end);

        // 2. 对换区无该文件数据数据
        if (swapTables == null || swapTables.size() == 0) {
            ArrayList<DiskSecondIndex> diskSecondIndices = diskDao.searchCommonByUFDId(ufdId);
            // TODO: 3. 将普通区文件写入对换区
            // 3.1 查看需要替换的长度内被其他文件占用的 ufdId
            // 3.2 将这些文件对应的 ufdId 置 0
            // 3.3 将普通区文件从 标志位sSwapOutFlag 开始放入
            // 3.4 改变对换区对换标志位标志位sSwapOutFlag

            // 4. 将普通区查找出的数据返回
            int temp = Math.min(diskSecondIndices.size(), end);
            for (int i = start; i <= temp; i++) {
                DiskResponse disk = new DiskResponse();
                disk.setContent(diskSecondIndices.get(i-1).getContent());
                disk.setFileOrder(diskSecondIndices.get(i-1).getFileOrder());
                result.add(disk);
            }
        } else {
            for (SwapTable swapTable : swapTables) {
                DiskResponse disk = new DiskResponse();
                disk.setContent(swapTable.getContent());
                disk.setFileOrder(swapTable.getFileOrder());
                result.add(disk);
            }
        }
        return result;
    }

    /**
     * 1. 查询磁盘是否够用
     * 2. 创建一级索引表，返回所有主键
     * 3. 创建二级索引表，写入数据
     * @param request
     * @return
     */
    @Override
    public int createFile(FileRequest request) {
        // 1. 查询磁盘是否够用，磁盘不够用返回-2
        ArrayList<Integer> freeDisks = diskDao.searchFreeDisk();
        if (freeDisks.size() < request.getLength()) {
            return -2;
        }

        // 2. 创建一级索引表
        ArrayList<DiskFirstIndex> diskFirstIndices = new ArrayList<>();
        int firstIndexLength = (int) Math.ceil((double) request.getLength()/Constants.SECOND_INDEX_TABLE_LENGTH);
        for (int i = 0; i < firstIndexLength; i++) {
            DiskFirstIndex diskFirstIndex = new DiskFirstIndex();
            diskFirstIndex.setUdfId(request.getUfdId());
            if (i == firstIndexLength - 1) {
                diskFirstIndex.setSecondIndexLength(request.getLength() % Constants.SECOND_INDEX_TABLE_LENGTH);
            } else {
                diskFirstIndex.setSecondIndexLength(Constants.SECOND_INDEX_TABLE_LENGTH);
            }
            diskFirstIndices.add(diskFirstIndex);
        }

        diskDao.createFirstIndexTable(diskFirstIndices);

        // 3. 创建二级索引表
        ArrayList<DiskSecondIndex> secondIndices = new ArrayList<>();

        String str = request.getContent();
        int j = -1;
        for (int i = 0; i < request.getLength(); i++) {
            int start = i * Constants.STRING_LENGTH_PER_DISK;
            int end = Math.min(start + Constants.STRING_LENGTH_PER_DISK, str.length());
            String substring = str.substring(start, end);
            if (i % 4 == 0) j++;

            DiskSecondIndex diskSecondIndex = new DiskSecondIndex();
            diskSecondIndex.setfDiskId(diskFirstIndices.get(j).getfDiskId());
            diskSecondIndex.setContent(substring);
            diskSecondIndex.setFileOrder(i+1);
            diskSecondIndex.setIsUseFlag(1);
            diskSecondIndex.setsDiskId(freeDisks.get(i));
            secondIndices.add(diskSecondIndex);
        }

        return diskDao.createSecondIndexTableBySId(secondIndices);
    }

    /**
     * 1. 根据ufdId查找一级索引表
     * 2. 根据一级索引表值 修改二级索引表
     * 3. 删掉一级索引表
     * 4. 删掉文件目录块
     * @param ufdId
     * @return
     */
    @Override
    public int deleteFile(int ufdId) {
        // 1. 查找一级索引表
        ArrayList<Integer> firstIndices = diskDao.searchFirstIndexByUFDId(ufdId);

        // 2. 修改二级索引表
        ArrayList<DiskSecondIndex> secondIndices = new ArrayList<>();
        for (Integer firstIndex : firstIndices) {
            DiskSecondIndex diskSecondIndex = new DiskSecondIndex();
            diskSecondIndex.setfDiskId(firstIndex);
            diskSecondIndex.setIsUseFlag(0);
            secondIndices.add(diskSecondIndex);
        }
        int updateSecondIndex = diskDao.updateSecondIndexByFId(secondIndices);

        // 3. 删除一级索引表
        int deleteFirstIndex = diskDao.deleteFirstIndexTable(firstIndices);

        // 4. 删除文件目录块
        int deleteFileDir = mFDDao.deleteFileDir(ufdId);

        return updateSecondIndex + deleteFirstIndex + deleteFileDir;
    }

    @Override
    public ArrayList<DiskBitmapResponse> showBitmap() {
        ArrayList<DiskBitmapResponse> result = new ArrayList<>();

        ArrayList<DiskSecondIndex> secondIndices = diskDao.showBitmap();
        for (DiskSecondIndex secondIndex : secondIndices) {
            DiskBitmapResponse diskBitmapResponse = new DiskBitmapResponse(secondIndex.getsDiskId(),
                    secondIndex.getIsUseFlag());
            result.add(diskBitmapResponse);
        }
        return result;
    }

}
