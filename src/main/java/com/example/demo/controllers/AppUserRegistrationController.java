package com.example.demo.controllers;

import lombok.AllArgsConstructor;
import com.example.demo.models.AppUser.AppUserRegistrationRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.services.AppUser.AppUserRegistrationService;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/registration")
public class AppUserRegistrationController {


    private final AppUserRegistrationService userRegistrationService;

    @PostMapping
    public String register(@RequestBody AppUserRegistrationRequest request) {

        return userRegistrationService.register(request);
    }
}
