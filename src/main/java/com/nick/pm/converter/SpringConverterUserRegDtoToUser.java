package com.nick.pm.converter;


import com.nick.pm.DTO.UserRegDTO;
import com.nick.pm.entity.Role;
import com.nick.pm.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SpringConverterUserRegDtoToUser implements Converter<UserRegDTO,User> {

    @Override
    public User convert(UserRegDTO userRegDTO) {
        User user = new User();
        user.setId(userRegDTO.getId());
        user.setUsername(userRegDTO.getUsername());
        user.setSurname(userRegDTO.getSurname());
        user.setEmail(userRegDTO.getEmail());
        String role = userRegDTO.getRole();
        if (role.equals(Role.DEVELOPER.toString())){
           user.setRole(Role.DEVELOPER);
        }else if (role.equals(Role.MANAGER.toString())){
            user.setRole(Role.MANAGER);
        }else{
            //todo throw something
        }
        user.setPassword(userRegDTO.getPassword());
        return user;
    }
}
