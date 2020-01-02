package com.example.os_be.domain.disk_management;

/**
 * 普通区采用二级索引表，扩大文件存储容量
 */
public class DiskSecondIndex {

    private int sDiskId;
    private int fDiskId;
    private String content;
    private int isUseFlag;
    private int fileOrder;
    private DiskFirstIndex diskFirstIndex;

    public int getsDiskId() {
        return sDiskId;
    }

    public void setsDiskId(int sDiskId) {
        this.sDiskId = sDiskId;
    }

    public int getfDiskId() {
        return fDiskId;
    }

    public void setfDiskId(int fDiskId) {
        this.fDiskId = fDiskId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIsUseFlag() {
        return isUseFlag;
    }

    public void setIsUseFlag(int isUseFlag) {
        this.isUseFlag = isUseFlag;
    }

    public int getFileOrder() {
        return fileOrder;
    }

    public void setFileOrder(int fileOrder) {
        this.fileOrder = fileOrder;
    }

    public DiskFirstIndex getDiskFirstIndex() {
        return diskFirstIndex;
    }

    public void setDiskFirstIndex(DiskFirstIndex diskFirstIndex) {
        this.diskFirstIndex = diskFirstIndex;
    }

    @Override
    public String toString() {
        return "DiskSecondIndex{" +
                "sDiskId=" + sDiskId +
                ", fDiskId=" + fDiskId +
                ", content='" + content + '\'' +
                ", isUseFlag=" + isUseFlag +
                ", fileOrder=" + fileOrder +
                '}';
    }
}
