package com.nick.pm.converter;


import com.nick.pm.DTO.UserDTO;
import com.nick.pm.entity.User;
import org.springframework.core.convert.converter.Converter;

public class SpringConverterUserToUserDTO implements Converter<User,UserDTO> {

    @Override
    public UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole().toString());
        return userDTO;
    }
}
