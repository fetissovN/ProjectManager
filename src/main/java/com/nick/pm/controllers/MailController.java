package com.nick.pm.controllers;

import com.nick.pm.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MailController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/confirm/{userId}/**")
    @ResponseBody
    public String confirmUser(@PathVariable Long userId, HttpServletRequest request){
        StringBuffer requestURL = request.getRequestURL();
        int i = requestURL.indexOf("confirm/");
        StringBuffer replace = requestURL.replace(0, i + 10,"");
        String encoded = replace.toString();
        userService.confirmUser(userId,encoded);
        return "done";
    }
}
