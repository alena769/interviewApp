package com.example.demo.models.AppUser;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.demo.services.AppUser.AppUserService;

//naplneni zkusebni dtb daty


@Component
@RequiredArgsConstructor
public class AppUserRunner implements CommandLineRunner {

    private final AppUserService userService;

    @Override
    public void run(String... args) throws Exception {
        //1
        AppUser helen = new AppUser("Helen", "Smith", "1@gmail.com",
                "mypass", "+420754001235");
        userService.signUpAppUser(helen);
        //2
        AppUser mark = new AppUser("Mark", "Stowe", "2@gmail.com",
                "mypass", "+001457865364");
        userService.signUpAppUser(mark);
        //3
        AppUser cris = new AppUser("Cris", "Big", "3@gmail.com",
                "mypass", "+49457865364");
        userService.signUpAppUser(cris);
        //4
        AppUser jam = new AppUser("Jam", "Traffic", "4@gmail.com",
                "mypass", "+001457865364");
        userService.signUpAppUser(jam);
        //5
        AppUser kraig = new AppUser("Kraig", "Svoboda", "5@gmail.com",
                "mama", "+001457865364");
        userService.signUpAppUser(kraig);
 }
}
