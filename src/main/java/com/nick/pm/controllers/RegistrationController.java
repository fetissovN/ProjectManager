package com.nick.pm.controllers;

import com.nick.pm.DTO.UserRegDTO;
import com.nick.pm.converter.SpringConverterUserRegDtoToUser;
import com.nick.pm.entity.User;
import com.nick.pm.service.user.UserService;
import com.nick.pm.utils.Strings;
import com.nick.pm.utils.mail.Mailing;
import com.nick.pm.utils.password.PassHash;
import com.nick.pm.validation.RegFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/reg")
public class RegistrationController extends ExceptionsController {

    @Value("${host}")
    private String HOST;

    @Autowired
    private RegFormValidator validator;

    @Autowired
    private UserService userService;

    @Autowired
    private SpringConverterUserRegDtoToUser springConverterUserRegDtoToUser;

    @RequestMapping(value = "/")
    public String showRegPage(Model model){
        model.addAttribute("newUser", new UserRegDTO());
        return "register";
    }

    @RequestMapping(value = "/do.reg")
    public String register(@ModelAttribute("newUser") UserRegDTO userRegDTO, Model model, BindingResult result){
        validator.validate(userRegDTO, result);
        if (result.hasErrors()){
            return "register";
        }
        User userFromDB = userService.getUserByEmail(userRegDTO.getEmail());
        if (userFromDB==null){
            try{
                User user = springConverterUserRegDtoToUser.convert(userRegDTO);
                long userCreatedId = userService.createUser(user);
                Mailing mailing = new Mailing(HOST);
                mailing.sendMailWithConfirmationLink(user.getEmail(),userCreatedId);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            model.addAttribute("newUser", new UserRegDTO());
            model.addAttribute("error", Strings.ERROR_USER_EXIST);
            return "register";
        }
        return "redirect:/log/";
    }
}
