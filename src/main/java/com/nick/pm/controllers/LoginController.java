package com.nick.pm.controllers;

import com.nick.pm.DTO.UserLoginDTO;
import com.nick.pm.converter.SpringConverterUserToUserDTO;
import com.nick.pm.entity.User;
import com.nick.pm.service.user.UserService;
import com.nick.pm.utils.login.SessionCheckLogin;
import com.nick.pm.utils.password.PassHash;
import com.nick.pm.validation.LoginFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping(value = "/log")
public class LoginController extends ExceptionsController {

    @Autowired
    private LoginFormValidator validator;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showLoginFrom(HttpServletRequest request , HttpServletResponse response, HttpSession session, Model model){

        if (SessionCheckLogin.checkLoggedInEither(session)){
            return "redirect:/";
        }else {
            model.addAttribute("loginForm", new UserLoginDTO());
            return "login";
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String login(@ModelAttribute(value = "loginForm") UserLoginDTO loginDTO, HttpServletResponse response, HttpSession session, Model model, BindingResult result){
        validator.validate(loginDTO, result);
        if (result.hasErrors()){
            return "login";
        }
        User userFromDB = userService.getUserByEmail(loginDTO.getEmail());
        if (userFromDB==null){
            model.addAttribute("loginErr", "loginErr");
            return "login";
        }else {
            PassHash passHash = new PassHash();
            String pass = passHash.stringPassToHash(loginDTO.getPassword());
            if (userFromDB.getPassword().equals(pass)){
                session.removeAttribute("auth");
                session.setAttribute("auth", new SpringConverterUserToUserDTO().convert(userFromDB));
                return "redirect:/";
            }else {
                model.addAttribute("loginErr", "loginErr");
                return "login";
            }
        }
    }

}
