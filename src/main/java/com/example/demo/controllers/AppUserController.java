package com.example.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.services.AppUser.AppUserService;

@Controller
@RequiredArgsConstructor
public class AppUserController {

    @Autowired
    private final AppUserService userService;

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("appUsers", userService.convertUsersToUsersDTO());

        return "index";
    }



}
