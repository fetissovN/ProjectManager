package com.nick.pm.converter;

import com.nick.pm.DTO.UserDTO;
import com.nick.pm.entity.User;
import com.nick.pm.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SpringConverterUserDTOToUser<T extends User> implements Converter<UserDTO, User>{

    @Autowired
    private UserService userService;

    public User convert(UserDTO userDTO){
        User user = userService.getUserById(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setSurname(userDTO.getSurname());
//        user.setRole(userDTO.getRole());
        user.setEmail(userDTO.getEmail());
        return user;
    }
}
