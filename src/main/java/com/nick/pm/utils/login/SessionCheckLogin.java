package com.nick.pm.utils.login;


import com.nick.pm.DTO.UserDTO;
import com.nick.pm.entity.Role;

import javax.servlet.http.HttpSession;

public class SessionCheckLogin{

    public static boolean checkLoggedInEither(HttpSession session){
        int role = checkLoggedInAs(session);
        if (role>=0 || role<=1){
            return true;
        }else {
            return false;
        }
    }

    public static boolean checkLoggedInDeveloper(HttpSession session){
        int role = checkLoggedInAs(session);
        if (role==1){
            return true;
        }else {
            return false;
        }
    }

    public static boolean checkLoggedInManager(HttpSession session){
        int role = checkLoggedInAs(session);
        if (role==0){
            return true;
        }else {
            return false;
        }
    }

    public static int checkLoggedInAs(HttpSession session){
        UserDTO user = (UserDTO) session.getAttribute("auth");
        if (user==null){
            return -1;
        }else if (user.getRole().toString().equals(Role.MANAGER)){
            return 0;
        }else if (user.getRole().toString().equals(Role.DEVELOPER)){
            return 1;
        }else {
            return -1;
        }
    }




}
