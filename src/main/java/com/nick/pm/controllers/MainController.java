package com.nick.pm.controllers;

import com.nick.pm.DTO.UserDTO;
import com.nick.pm.entity.Project;
import com.nick.pm.service.project.ProjectService;
import com.nick.pm.utils.login.SessionCheckLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainController extends ExceptionsController{

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/")
    public String homePage(HttpSession session, Model model ){
        if (session.getAttribute("auth")==null){
            return "redirect:/login";
        }else {
            UserDTO userDTO = (UserDTO) session.getAttribute("auth");
            if (SessionCheckLogin.checkLoggedInManager(session)){
                List<Project> projectsOfManager = projectService.getAllProjectsCreatedByManager(userDTO);
                model.addAttribute("list", projectsOfManager);
            }
            if (SessionCheckLogin.checkLoggedInDeveloper(session)){
                List<Project> projectsOfDeveloperTakePart = projectService.getAllProjectsDeveloperTakePart(userDTO);
                model.addAttribute("list", projectsOfDeveloperTakePart);
            }
            return "main";
        }
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }
}
