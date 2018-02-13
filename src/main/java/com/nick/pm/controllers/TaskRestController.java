package com.nick.pm.controllers;

import com.nick.pm.DTO.CommentDTO;
import com.nick.pm.DTO.TaskDTO;
import com.nick.pm.DTO.UserDTO;
import com.nick.pm.entity.Comment;
import com.nick.pm.entity.Task;
import com.nick.pm.service.comment.CommentService;
import com.nick.pm.service.task.TaskService;
import com.nick.pm.utils.DataJsonAddUserToProject;
import com.nick.pm.utils.DataJsonAddUserToTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/task")
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/getTaskInfo/{taskId}", method = RequestMethod.GET)
    public TaskDTO getTaskInfo(@PathVariable Long taskId, HttpSession session) {
        if (session.getAttribute("auth") == null) {
            return null;
        } else {
            return taskService.getTaskById(taskId);
        }

    }

    @RequestMapping(value = "/getDevelopersOfTask/{taskId}", method = RequestMethod.GET)
    public List<UserDTO> getDevelopersOfTask(@PathVariable Long taskId, HttpSession session) {
        if (session.getAttribute("auth") == null) {
            return null;
        } else {
            List<UserDTO> userDTOs = taskService.getDevelopersOfTask(taskId);
            return userDTOs;
        }

    }

    @RequestMapping(value = "/getAllComments/{taskId}", method = RequestMethod.GET)
    public List<CommentDTO> getAllComments(@PathVariable Long taskId, HttpSession session) {
        if (session.getAttribute("auth") == null) {
            return null;
        } else {
            List<CommentDTO> commentDTOs = taskService.getAllComments(taskId);
            return commentDTOs;
        }
    }

    @RequestMapping(value = "/saveNewComment", method = RequestMethod.POST
            ,produces = "application/json"
            ,consumes = "application/json")
    public CommentDTO saveNewComment(@RequestBody  CommentDTO commentDTO, HttpSession session) {
        if (session.getAttribute("auth") == null) {
            return null;
        } else {
            commentDTO.setComment_date(new Date());
            return commentService.saveComment(commentDTO);
        }
    }

    @RequestMapping(value = "/addDeveloperToTask", method = RequestMethod.POST)
    public String getDevelopersOfTask(@RequestBody DataJsonAddUserToTask json, HttpSession session) {
        if (session.getAttribute("auth") == null) {
            return null;
        } else {
            taskService.addDeveloperToTask(json.getDeveloperId(), json.getTaskId());
            return "saved";
        }

    }
}
