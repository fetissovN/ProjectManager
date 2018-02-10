package com.nick.pm.controllers;


import com.nick.pm.DTO.UserDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/main")
public class MainRESTController {

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET
            ,produces = "application/json")
    public Map<String, UserDTO> getUserInfo(HttpSession session){
        Map<String, UserDTO> map = new HashMap<>();
        if (session.getAttribute("auth")==null){
            map.put("user", null);
            return map;
        }else {
            UserDTO userDTO = (UserDTO) session.getAttribute("auth");
            if (userDTO != null){
                map.put("user",userDTO);
            }
            return map;
        }
    }
}
