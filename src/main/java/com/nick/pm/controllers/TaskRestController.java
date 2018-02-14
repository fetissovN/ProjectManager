package com.nick.pm.controllers;

import com.nick.pm.DTO.CommentDTO;
import com.nick.pm.DTO.TaskDTO;
import com.nick.pm.DTO.UserDTO;
import com.nick.pm.service.comment.CommentService;
import com.nick.pm.service.task.TaskService;
import com.nick.pm.utils.DataJsonAddUserToTask;
import com.nick.pm.utils.DataJsonTaskStatus;
import com.nick.pm.utils.DataJsonUpdateComment;
import com.nick.pm.utils.login.SessionCheckLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Controller handling requests from task page
 * Consumes - json
 * Produces - json,String
 */
@RestController
@RequestMapping(value = "/api/task")
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private SessionCheckLogin checkLogin;

    @RequestMapping(value = "/getTaskInfo/{taskId}", method = RequestMethod.GET)
    public TaskDTO getTaskInfo(@PathVariable Long taskId, HttpSession session) {
        if (!checkLogin.checkLoggedIn(session)) {
            return null;
        } else {
            return taskService.getTaskById(taskId);
        }

    }

    @RequestMapping(value = "/getDevelopersOfTask/{taskId}", method = RequestMethod.GET)
    public List<UserDTO> getDevelopersOfTask(@PathVariable Long taskId, HttpSession session) {
        if (!checkLogin.checkLoggedIn(session)) {
            return null;
        } else {
            return taskService.getDevelopersOfTask(taskId);
        }

    }

    @RequestMapping(value = "/getAllComments/{taskId}", method = RequestMethod.GET)
    public List<CommentDTO> getAllComments(@PathVariable Long taskId, HttpSession session) {
        if (!checkLogin.checkLoggedIn(session)) {
            return null;
        } else {
            return taskService.getAllComments(taskId);
        }
    }

    @RequestMapping(value = "/saveNewComment", method = RequestMethod.POST
            ,produces = "application/json"
            ,consumes = "application/json")
    public CommentDTO saveNewComment(@RequestBody  CommentDTO commentDTO, HttpSession session) {
        if (!checkLogin.checkLoggedIn(session)) {
            return null;
        } else {
            commentDTO.setComment_date(new Date());
            return commentService.saveComment(commentDTO);
        }
    }

    @RequestMapping(value = "/addDeveloperToTask", method = RequestMethod.POST)
    public String getDevelopersOfTask(@RequestBody DataJsonAddUserToTask json, HttpSession session) {
        if (!checkLogin.checkLoggedIn(session)) {
            return null;
        } else {
            taskService.addDeveloperToTask(json.getDeveloperId(), json.getTaskId());
            return "saved";
        }
    }

    @RequestMapping(value = "/deleteComment/{commentId}", method = RequestMethod.DELETE)
    public String deleteComment(@PathVariable Long commentId, HttpSession session) {
        if (!checkLogin.checkLoggedIn(session)) {
            return null;
        } else {
            commentService.deleteComment(commentId);
            return "deleted";
        }
    }

    @RequestMapping(value = "/updateComment", method = RequestMethod.POST)
    public String updateComment(@RequestBody DataJsonUpdateComment data, HttpSession session) {
        if (!checkLogin.checkLoggedIn(session)) {
            return null;
        } else {
            commentService.updateComment(data.getText(),data.getCommentId());
            return "updated";
        }
    }

    @RequestMapping(value = "/updateTaskStatus", method = RequestMethod.POST)
    public String updateTaskStatus(@RequestBody DataJsonTaskStatus data, HttpSession session) {
        if (!checkLogin.checkLoggedIn(session)) {
            return null;
        } else {
            taskService.changeStatus(data.getTaskId(),data.getStatus());
            return "updated";
        }
    }
}
