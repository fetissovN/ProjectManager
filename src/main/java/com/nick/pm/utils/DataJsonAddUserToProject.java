package com.nick.pm.utils;


/**
 * Object for json
 * For adding user to project
 */
public class DataJsonAddUserToProject {

    private long developerId;

    private long projectId;

    public DataJsonAddUserToProject() {
    }

    public long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(long developerId) {
        this.developerId = developerId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }
}
