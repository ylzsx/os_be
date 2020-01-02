package com.example.os_be.domain;

public class Constants {

    // 磁盘普通区块数
    public static final int DISK_COMMON_AREA = 900;
    // 磁盘对换区块数
    public static final int DISK_SWAP_AREA = 124;
    // 每块磁盘能放的字符窜长度
    public static final int STRING_LENGTH_PER_DISK = 4;

    // 二级索引表长度
    public static final int SECOND_INDEX_TABLE_LENGTH = 4;
    // 每次往内存传送的磁盘块数
    public static final int BLOCK_SIZE_PER_TIME = 4;

    // 对换区换出文件的位置
    public static int sSwapOutFlag = 1;
}
