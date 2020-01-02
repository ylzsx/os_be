package com.example.os_be.domain.disk_management;

import java.util.ArrayList;

public class DiskFirstIndex {

    private int fDiskId;
    private int udfId;
    private int secondIndexLength;

    public int getfDiskId() {
        return fDiskId;
    }

    public void setfDiskId(int fDiskId) {
        this.fDiskId = fDiskId;
    }

    public int getUdfId() {
        return udfId;
    }

    public void setUdfId(int udfId) {
        this.udfId = udfId;
    }

    public int getSecondIndexLength() {
        return secondIndexLength;
    }

    public void setSecondIndexLength(int secondIndexLength) {
        this.secondIndexLength = secondIndexLength;
    }
}
