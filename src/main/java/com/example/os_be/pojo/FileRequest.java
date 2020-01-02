package com.example.os_be.pojo;

public class FileRequest {

    private int ufdId;
    private String content;
    // 文件块长度，按照每个磁盘块中可存放的字符串长度计算，即所需要的磁盘块数
    private int length = -1;

    public int getUfdId() {
        return ufdId;
    }

    public void setUfdId(int ufdId) {
        this.ufdId = ufdId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
