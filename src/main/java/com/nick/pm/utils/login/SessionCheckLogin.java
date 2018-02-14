package com.nick.pm.utils.login;


import com.nick.pm.DTO.UserDTO;
import com.nick.pm.entity.Role;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * Class checking user role
 */
@Component
public class SessionCheckLogin{

    public boolean checkLoggedIn(HttpSession session){
        int role = checkLoggedInAs(session);
        return role >= 0 && role <= 1;
    }

    public boolean checkLoggedInDeveloper(HttpSession session){
        int role = checkLoggedInAs(session);
        return role == 1;
    }

    public boolean checkLoggedInManager(HttpSession session){
        int role = checkLoggedInAs(session);
        return role == 0;
    }

    private int checkLoggedInAs(HttpSession session){
        UserDTO user = (UserDTO) session.getAttribute("auth");
        if (user==null){
            return -1;
        }else if (user.getRole().equals(Role.MANAGER.toString())){
            return 0;
        }else if (user.getRole().equals(Role.DEVELOPER.toString())){
            return 1;
        }else {
            return -1;
        }
    }
}
