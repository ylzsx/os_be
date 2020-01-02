package com.example.os_be.domain.directory_management;

public class UFD {

    private int ufdId;
    private int userId;
    private String fileName;
    private int fileLength;
    private int isOpenFlag = -1;

    public int getUfdId() {
        return ufdId;
    }

    public void setUfdId(int ufdId) {
        this.ufdId = ufdId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileLength() {
        return fileLength;
    }

    public void setFileLength(int fileLength) {
        this.fileLength = fileLength;
    }

    public int getIsOpenFlag() {
        return isOpenFlag;
    }

    public void setIsOpenFlag(int isOpenFlag) {
        this.isOpenFlag = isOpenFlag;
    }
}
