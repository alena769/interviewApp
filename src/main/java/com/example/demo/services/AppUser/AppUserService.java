package com.example.demo.services.AppUser;

import com.example.demo.models.AppUser.AppUser;
import com.example.demo.models.AppUser.AppUserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppUserService {
    String signUpAppUser(AppUser appUser);
    void updateAppUser(AppUser appUser);
    void deleteAppUser(AppUser appUser);
    List<AppUser> orderAppUsersByDateAsc();
    List<AppUser> orderAppUsersByDateDesc();
    List<AppUser> findAllUsers();
    List<AppUser> findByKeyword(String keyword);
    AppUser findByEmail(String email);
    List<AppUserDTO> convertAppUsersToDTO(List<AppUser> appUserList);
    AppUserDTO convertAppUserToDTO(AppUser appUser);
    AppUser findUserById(Long id);
    List<AppUser> findByIsActive(Boolean isActive);


}
