package com.nick.pm.dao.task;

import com.nick.pm.entity.Task;

import java.util.List;

public interface TaskDAO {

    void createTask(Task task);

    List getAllTasks();

    Task getTaskById(Long postId);

    void updateTask(Task task);

    void deleteTask(Task task);

}
