package com.nick.pm.utils;

/**
 * Object for json
 * For holding status of task
 */
public class DataJsonTaskStatus {

    private long taskId;

    private String status;

    public DataJsonTaskStatus() {
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
