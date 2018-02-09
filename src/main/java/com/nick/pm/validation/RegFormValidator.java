package com.nick.pm.validation;


import com.nick.pm.DTO.UserDTO;
import com.nick.pm.DTO.UserRegDTO;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RegFormValidator extends ValidatorSample implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserRegDTO user = (UserRegDTO) o;

        validNotBlank(errors, "username", "reg.username.empty", "Username must not be empty.");
        valid(errors,user.getUsername(),"username", "reg.username.tooLong", "Username must not more than 20 characters.",20);

        validNotBlank(errors, "surname", "reg.surname.empty", "Surname must not be empty.");
        valid(errors,user.getUsername(),"surname", "reg.surname.tooLong", "Username must not more than 20 characters.",20);

        validNotBlank(errors,"password", "reg.password.empty", "Password must not be empty.");
        if (!(user.getPassword()).equals(user
                .getPasswordCheck())) {
            errors.rejectValue("passwordCheck", "reg.passwordCheck.passwordDontMatch", "Passwords don't match.");
        }

        if( !EmailValidator.getInstance().isValid( user.getEmail() ) ){
            errors.rejectValue("email", "reg.email.notValid", "Email address is not valid.");
        }
    }
}
