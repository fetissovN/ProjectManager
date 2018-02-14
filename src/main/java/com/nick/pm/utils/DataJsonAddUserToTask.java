package com.nick.pm.utils;

/**
 * Object for json
 * For adding user to task
 */
public class DataJsonAddUserToTask {

    private long developerId;

    private long taskId;

    public DataJsonAddUserToTask() {
    }

    public long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(long developerId) {
        this.developerId = developerId;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }
}
