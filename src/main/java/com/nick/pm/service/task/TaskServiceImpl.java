package com.nick.pm.service.task;

import com.nick.pm.dao.task.TaskDAO;
import com.nick.pm.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDAO taskDAO;

    @Override
    public void createTask(Task task) {
        taskDAO.createTask(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskDAO.getAllTasks();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskDAO.getTaskById(id);
    }

    @Override
    public void deleteTaskById(Long id) {
        Task task = getTaskById(id);
        taskDAO.deleteTask(task);
    }

    @Override
    public void deleteTask(Task task) {
        taskDAO.deleteTask(task);
    }

    @Override
    public void updateTask(Task task, Long id) {
        taskDAO.updateTask(task);
    }
}

