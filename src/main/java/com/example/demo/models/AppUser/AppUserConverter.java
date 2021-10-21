package com.example.demo.models.AppUser;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppUserConverter {

    public AppUserDTO userToDTO(AppUser user) {

        AppUserDTO dto = new AppUserDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setIsActive(user.getIsActive());
        dto.setDateCreated(user.getDateCreated());

        return dto;
    }

    public List<AppUserDTO> listOfUsersToDTO(List<AppUser> users){
        return users.stream().map(user -> userToDTO(user)).collect(Collectors.toList());
    }
}
