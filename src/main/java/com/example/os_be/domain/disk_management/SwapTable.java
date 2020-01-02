package com.example.os_be.domain.disk_management;

/**
 * 对换区采用一级索引表，加快查找速度
 */
public class SwapTable {

    private int swapTableId;
    private String content;
    private int isUseFlag = 0;
    private int ufdId;
    private int fileOrder;

    public int getSwapTableId() {
        return swapTableId;
    }

    public void setSwapTableId(int swapTableId) {
        this.swapTableId = swapTableId;
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

    public int getUfdId() {
        return ufdId;
    }

    public void setUfdId(int ufdId) {
        this.ufdId = ufdId;
    }

    public int getFileOrder() {
        return fileOrder;
    }

    public void setFileOrder(int fileOrder) {
        this.fileOrder = fileOrder;
    }

    @Override
    public String toString() {
        return "SwapTable{" +
                "swapTableId=" + swapTableId +
                ", content='" + content + '\'' +
                ", isUseFlag=" + isUseFlag +
                ", ufdId=" + ufdId +
                ", fileOrder=" + fileOrder +
                '}';
    }
}
