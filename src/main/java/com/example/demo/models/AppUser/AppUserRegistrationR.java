package com.example.demo.models.AppUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class AppUserRegistrationR {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String telephoneNumber;

}