package com.example.os_be.pojo;

public class DiskBitmapResponse {

    private int diskId;
    private int isUseFlag;

    public DiskBitmapResponse() {}

    public DiskBitmapResponse(int diskId, int isUseFlag) {
        this.diskId = diskId;
        this.isUseFlag = isUseFlag;
    }

    public int getDiskId() {
        return diskId;
    }

    public void setDiskId(int diskId) {
        this.diskId = diskId;
    }

    public int getIsUseFlag() {
        return isUseFlag;
    }

    public void setIsUseFlag(int isUseFlag) {
        this.isUseFlag = isUseFlag;
    }
}
