package com.example.demo.services.AppUser;

import com.example.demo.models.AppUser.AppUser;
import com.example.demo.models.AppUser.AppUserRegistrationRequest;
import java.util.List;

import com.example.demo.services.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserRegistrationService implements UserDetailsService {

    @Autowired
    private final EmailValidator emailValidator;
    private final static String EMAIL_NOT_VALID ="email %s is not valid";
    private final AppUserService appUserService;

    public String register(AppUserRegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if(!isValidEmail) {
            throw new IllegalStateException(String.format(EMAIL_NOT_VALID,request.getEmail()));
        }

        return appUserService.signUpAppUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        request.getTelephoneNumber()
                )
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("root",
                "$2a$10$Lw6CEI7yNl.i1dVaUYJhO.pEgpQuQHP10eyYW8YbVC4gWpJvgt3ly",
                List.of(new SimpleGrantedAuthority("ADMIN")));
    }
}
