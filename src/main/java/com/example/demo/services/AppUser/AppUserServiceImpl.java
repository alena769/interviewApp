package com.example.demo.services.AppUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.demo.models.AppUser.AppUser;
import com.example.demo.models.AppUser.AppUserConverter;
import com.example.demo.models.AppUser.AppUserDTO;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.repositories.AppUser.AppUserRepository;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AppUserServiceImpl implements AppUserService {


    private final static String USER_NOT_FOUND = "user %s not found";
    private final static String USER_EXISTS = "user with email: %s, already exists";
    private final AppUserRepository appUserRepository;
    private final AppUserConverter appUserConverter;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AppUser findByEmail(String email) {
        log.info("fetching user by email {}", email);

       return appUserRepository.findByEmail(email)
               .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));

    }

    @Override
    public String signUpAppUser(AppUser appUser) {
        log.info("signing up new user{}",appUser.getFirstName());

        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();

        if(userExists){
            throw new IllegalStateException(String.format(USER_EXISTS,appUser.getEmail()));
        }else {
           String encodedPass = bCryptPasswordEncoder.encode(appUser.getPassword());
           appUser.setPassword(encodedPass);
           appUserRepository.save(appUser);
        }
        return null; //TODO send email
    }

    @Override
    public void updateAppUser(AppUser appUser) {

            AppUser appUserToUpdate = findUserById(appUser);
            if(appUserToUpdate != null) {
                log.info("updating user {}", appUser.getFirstName());
                appUserToUpdate.setFirstName(appUser.getFirstName());
                appUserToUpdate.setLastName(appUser.getLastName());
                appUserToUpdate.setEmail(appUser.getEmail());
                appUserToUpdate.setPassword(appUser.getPassword());
                appUserToUpdate.setIsActive(appUser.getIsActive());
                appUserToUpdate.setTelephoneNumber(appUser.getTelephoneNumber());
            }else throw new UsernameNotFoundException(String.format(USER_NOT_FOUND, appUser.getId()));
    }

    @Override
    public void deleteAppUser(AppUser appUser) {

        if(findUserById(appUser) != null) {
            log.info("deleting user {}", appUser.getFirstName());
            appUserRepository.deleteById(appUser.getId());
        }else throw new UsernameNotFoundException(String.format(USER_NOT_FOUND, appUser.getId()));
    }

    @Override
    public List<AppUser> orderAppUsersByDateAsc() {
        log.info("list of all users sorted by date asc");

        List<AppUser> sortedUserListAsc = appUserRepository.listAllAppUsers();
        Collections.sort(sortedUserListAsc);

        return sortedUserListAsc;
    }

    @Override
    public List<AppUser> orderAppUsersByDateDesc() {
        log.info("list all users sorted by date desc");

        List<AppUser> sortedUserListDesc = appUserRepository.listAllAppUsers();
        sortedUserListDesc.sort(Collections.reverseOrder());

        return sortedUserListDesc;
    }

    @Override
    public List<AppUser> findAllUsers() {
        return appUserRepository.listAllAppUsers();
    }

    @Override
    public List<AppUserDTO> convertUsersToUsersDTO() {
        return appUserConverter.listOfUsersToDTO(appUserRepository.listAllAppUsers());
    }

    public AppUser findUserById(AppUser appUser) {
        log.info("fetching user {} by id", appUser.getFirstName());

        return appUserRepository.findById(appUser.getId())
                .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND, appUser.getId())));
    }

}
