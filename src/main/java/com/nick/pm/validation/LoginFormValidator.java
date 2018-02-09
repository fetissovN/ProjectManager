package com.nick.pm.validation;

import com.nick.pm.DTO.UserLoginDTO;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class LoginFormValidator extends ValidatorSample implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserLoginDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        UserLoginDTO user = (UserLoginDTO) o;

//        validNotBlank(errors,"email","login.email.empty","Username must not be empty.");
//        valid(errors,user.getEmail(),"email","login.email.tooLong","Login must not more than 30 characters.",30);
        if( !EmailValidator.getInstance().isValid( user.getEmail() ) ){
            errors.rejectValue("email", "reg.email.notValid", "Email address is not valid.");
        }
        validNotBlank(errors,"password","login.password.empty","Password must not be empty.");

    }

}
