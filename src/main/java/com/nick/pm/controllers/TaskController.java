package com.nick.pm.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class TaskController {

    @RequestMapping(value = "/task/{taskId}", method = RequestMethod.GET)
    public String homePage(@PathVariable Long taskId, HttpSession session, Model model ) {
        if (session.getAttribute("auth") == null) {
            return "redirect:/log/";
        } else {
            model.addAttribute("id", taskId);
            return "task";
        }

    }
}
