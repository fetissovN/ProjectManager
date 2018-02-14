package com.nick.pm.controllers;

import com.nick.pm.utils.login.SessionCheckLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


/**
 * Controller handling loading main page
 */
@Controller
public class MainController extends ExceptionsController{


    @Autowired
    private SessionCheckLogin checkLogin;

    @RequestMapping(value = "/")
    public String homePage(HttpSession session, Model model ){
        if (!checkLogin.checkLoggedIn(session)){
            return "redirect:/log/";
        }else {
            return "main";
        }
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }
}
