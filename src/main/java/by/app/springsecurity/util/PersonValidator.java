package by.app.springsecurity.util;

import by.app.springsecurity.model.Person;
import by.app.springsecurity.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Vladislav Domaniewski
 */

@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
         return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        try {
            peopleService.loadUserByUsername(person.getUsername());
        } catch (UsernameNotFoundException ignored) {
            return;  // Если исключение не выброшено значит всё ок, и пользователдь не найден
        }
        errors.rejectValue("username", "" , "Человек с таким именем существует ");
    }
}
