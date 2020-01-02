package com.example.os_be.dao;

import com.example.os_be.domain.disk_management.DiskFirstIndex;
import com.example.os_be.domain.disk_management.DiskSecondIndex;
import com.example.os_be.domain.disk_management.SwapTable;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface IDiskDao {

    // 根据用户文件 ufdId 查找对换区
    ArrayList<SwapTable> searchSwapByUFDId(@Param("ufdId") int ufdId,
                                                  @Param("start") int start,
                                                  @Param("end") int end);

    // 根据用户文件 ufdId 查找普通区
    ArrayList<DiskSecondIndex> searchCommonByUFDId(@Param("ufdId") int ufdId);

    // 将普通区磁盘信息写入对换区, 返回影响的条数
    int updateSwap(ArrayList<DiskSecondIndex> diskSecondIndices);

    // 查询未被占用的磁盘块
    ArrayList<Integer> searchFreeDisk();

    // 展示位示图
    ArrayList<DiskSecondIndex> showBitmap();



    // 创建一级索引表，返回创建成功的主键id数组
    int createFirstIndexTable(ArrayList<DiskFirstIndex> diskFirstIndices);

    // 删除一级索引表
    int deleteFirstIndexTable(ArrayList<Integer> firstIndices);

    // 根据用户文件 ufdId 查找一级索引表
    ArrayList<Integer> searchFirstIndexByUFDId(@Param("ufdId") int ufdId);

    // 通过二级索引编号更改(创建)二级索引表，返回影响的条数
    int createSecondIndexTableBySId(ArrayList<DiskSecondIndex> diskSecondIndices);

    // 通过一级索引编号更改二级索引表，返回影响的条数
    int updateSecondIndexByFId(ArrayList<DiskSecondIndex> diskSecondIndices);
}
