package com.nick.pm.controllers;


import com.nick.pm.DTO.UserDTO;
import com.nick.pm.entity.Project;
import com.nick.pm.service.user.UserService;
import com.nick.pm.utils.login.SessionCheckLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping(value = "/api/main")
public class MainRESTController {

    @Autowired
    private UserService userService;

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
    public List<Project> getUserProjects(@PathVariable Long id, HttpSession session){
        List<Project> projectList = new ArrayList<>();
        if (session.getAttribute("auth")==null){
            projectList.add(null);
            return projectList;
        }else {
            List<Project> projects = userService.getProjectsCreatedByUser(id);
            projectList.addAll(projects);
            return projectList;
        }
    }
}
