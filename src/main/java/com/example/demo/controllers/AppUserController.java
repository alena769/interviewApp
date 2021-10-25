package com.example.demo.controllers;

import com.example.demo.models.AppUser.AppUser;
import com.example.demo.models.AppUser.AppUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.AppUser.AppUserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService userService;

    //------HOME page, list of all users --------------------
    @GetMapping("/")
    public String home (Model model){
        List<AppUser> appUserList = userService.findAllUsers();
        model.addAttribute("users", userService.convertAppUsersToDTO(appUserList));

        return "index";
    }
    //--------Search podle eamil ---------------------------
    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    @ResponseBody
    public AppUserDTO findByEmail(@PathVariable String email){
        AppUser appUser = userService.findByEmail(email);
        return userService.convertAppUserToDTO(appUser);
    }
    //--------Search podle id ---------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AppUserDTO findById(@PathVariable Long id){
        AppUser appUser = userService.findUserById(id);
        return userService.convertAppUserToDTO(appUser);
    }

    //--------Search podle jmena a prijmeni ---------------------------

    @RequestMapping(value = "{keyword}", method = RequestMethod.GET)
    @ResponseBody
    public List<AppUserDTO> findByKeyword(@PathVariable String keyword){
        List<AppUser> appUserList = userService.findByKeyword(keyword);

        return userService.convertAppUsersToDTO(appUserList);
    }

    //--------Search podle statusu ---------------------------
    @RequestMapping(value = "{status}", method = RequestMethod.GET)
    @ResponseBody
    public List<AppUserDTO> findByIsActive(@PathVariable Boolean status){

        List<AppUser> appUserList = userService.findByIsActive(status);

        return userService.convertAppUsersToDTO(appUserList);
    }
    //--------Search podle data vytvoreni ASC ---------------------------
    @RequestMapping(value = "/datumAsc", method = RequestMethod.GET)
    public String listUsersByDateAsc(Model model){

        model.addAttribute("users", userService.orderAppUsersByDateAsc());

        return "redirect:/";
    }
    //--------Search podle data vytvoreni DESC ---------------------------

    @RequestMapping(value = "/datumDesc", method = RequestMethod.GET)
    public String listUsersByDateDesc(Model model){

        model.addAttribute("users", userService.orderAppUsersByDateDesc());

        return "redirect:/";
    }
    //---------------UPDATE--------------------
    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.PUT})
    public String update(AppUser appUser){

        AppUser user = userService.findUserById(appUser.getId());
        user.setFirstName(appUser.getFirstName());
        user.setLastName(appUser.getLastName());
        user.setEmail(appUser.getEmail());
        user.setPassword(appUser.getPassword());
        user.setTelephoneNumber(appUser.getTelephoneNumber());
        user.setIsActive(appUser.getIsActive());

        userService.updateAppUser(user);

        return "redirect:/";
    }

    //-----------DELETE-----------------------------
    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE,RequestMethod.GET})
    public String delete(Long id){
        AppUser user = userService.findUserById(id);
        userService.deleteAppUser(user);

        return "redirect:/";
    }

}
