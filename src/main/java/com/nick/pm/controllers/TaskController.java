package com.nick.pm.controllers;


import com.nick.pm.utils.login.SessionCheckLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class TaskController {

    @Autowired
    private SessionCheckLogin checkLogin;

    @RequestMapping(value = "/task/{taskId}", method = RequestMethod.GET)
    public String homePage(@PathVariable Long taskId, HttpSession session, Model model ) {
        if (!checkLogin.checkLoggedIn(session)) {
            return "redirect:/log/";
        } else {
            model.addAttribute("id", taskId);
            return "task";
        }

    }
}
