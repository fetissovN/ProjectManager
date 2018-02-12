package com.nick.pm.controllers;


import com.nick.pm.DTO.ProjectDTO;
import com.nick.pm.DTO.TaskDTO;
import com.nick.pm.DTO.UserDTO;
import com.nick.pm.entity.Project;
import com.nick.pm.entity.Task;
import com.nick.pm.entity.User;
import com.nick.pm.service.project.ProjectService;
import com.nick.pm.service.task.TaskService;
import com.nick.pm.service.user.UserService;
import com.nick.pm.utils.DataJsonAddUserToProject;
import com.nick.pm.utils.login.SessionCheckLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping(value = "/api/main")
public class MainRESTController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET
            ,produces = "application/json")
    public Map<String, UserDTO> getUserInfo(HttpSession session){
        Map<String, UserDTO> map = new HashMap<>();
        if (session.getAttribute("auth")==null){
            map.put("user", null);
            return map;
        }else {
            UserDTO userDTO = (UserDTO) session.getAttribute("auth");
            if (userDTO != null){
                map.put("user",userDTO);
            }
            return map;
        }
    }

    @RequestMapping(value = "/getUserProjects/{id}", method = RequestMethod.GET
            ,produces = "application/json")
    public List<ProjectDTO> getUserProjects(@PathVariable Long id, HttpSession session){
        List<ProjectDTO> projectList = new ArrayList<>();
        if (session.getAttribute("auth")==null){
            projectList.add(null);
            return projectList;
        }else {
            List<ProjectDTO> projects = userService.getProjectsCreatedByUser(id);
            projectList.addAll(projects);
            return projectList;
        }
    }

    @RequestMapping(value = "/saveNewProject/{id}", method = RequestMethod.POST
            ,produces = "application/json")
    public String saveNewProject(@ModelAttribute Project project, @PathVariable Long id, HttpSession session){
        if (session.getAttribute("auth")==null){
            return "authFail";
        }else {
            projectService.saveNewProject(project,id);
            return "saved";
        }
    }

    @RequestMapping(value = "/getProjectTasks/{id}", method = RequestMethod.GET
            ,produces = "application/json")
    public List<TaskDTO> getProjectTasks(@PathVariable Long id, HttpSession session){
        if (session.getAttribute("auth")==null){
            return null;
        }else {
            List<TaskDTO> taskList = projectService.getTasksOfProject(id);
            return taskList;
        }
    }


    @RequestMapping(value = "/saveNewTask/{projectId}/user/{userId}", method = RequestMethod.POST
            ,produces = "application/json")
    public String saveNewTask(@ModelAttribute TaskDTO taskDTO, @PathVariable Long projectId, @PathVariable Long userId, HttpSession session){
        if (session.getAttribute("auth")==null){
            return "authFail";
        }else {
            taskService.saveNewTask(taskDTO);
            return "saved";
        }
    }
    @RequestMapping(value = "/getAllDevelopersOfProject/{id}", method = RequestMethod.GET
            ,produces = "application/json")
    public List<UserDTO> getAllDevelopersOfProject(@PathVariable Long id, HttpSession session){
        if (session.getAttribute("auth")==null){
            return null;
        }else {
            return userService.getAllDevelopersOfProject(id);
        }
    }

    @RequestMapping(value = "/getAllDevelopers", method = RequestMethod.GET
            ,produces = "application/json")
    public List<UserDTO> getAllDevelopers(HttpSession session){
        if (session.getAttribute("auth")==null){
            return null;
        }else {
            return userService.getAllDevelopers();
        }
    }

    @RequestMapping(value = "/addDeveloperToProject", method = RequestMethod.POST
            ,produces = "application/json")
    public String getAllDevelopers(@RequestBody DataJsonAddUserToProject json, HttpSession session){
        if (session.getAttribute("auth")==null){
            return null;
        }else {
            userService.addDeveloperToProject(json.getDeveloperId(), json.getProjectId());
            return "saved";
        }
    }
}
