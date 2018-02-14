package com.nick.pm.controllers;

import com.nick.pm.DTO.UserLoginDTO;
import com.nick.pm.converter.SpringConverterUserToUserDTO;
import com.nick.pm.entity.User;
import com.nick.pm.service.user.UserService;
import com.nick.pm.utils.Strings;
import com.nick.pm.utils.login.SessionCheckLogin;
import com.nick.pm.validation.LoginFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Controller handling operations with login
 */
@Controller
@RequestMapping(value = "/log")
public class LoginController extends ExceptionsController {

    @Autowired
    private LoginFormValidator validator;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionCheckLogin checkLogin;

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.setAttribute("auth",null);
        return "redirect:/log/";

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showLoginFrom(HttpSession session, Model model){

        if (checkLogin.checkLoggedIn(session)){
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
            model.addAttribute("loginErr", Strings.ERROR_LOGIN_USER_NOT_EXIST);
            return "login";
        }else {
            if (userFromDB.getActive() == 0){
                model.addAttribute("loginErr", Strings.NOT_ACTIVE);
                return "login";
            }else {
                org.springframework.security.crypto.password.PasswordEncoder encoder
                        = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
                String loginPass = encoder.encode(loginDTO.getPassword());

                Boolean matches = encoder.matches(loginDTO.getPassword(), userFromDB.getPassword());

                if (matches){
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

}
