package kosy.todo.todo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import kosy.todo.todo.domain.User;
import kosy.todo.todo.service.UserService;


@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass){
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors){

        User user = (User) object;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        if(user.getName().length() < 6 || user.getName().length() > 16){
            errors.rejectValue("name", "Size.user.name");
        }
        if(userService.findByName(user.getName()) != null){
            errors.rejectValue("name", "Duplicate.user.name");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if(user.getPassword().length() < 8 || user.getPassword().length() > 16){
            errors.rejectValue("password", "Size.user.password");
        }

    }

}