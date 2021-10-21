package com.example.demo.models.AppUser;

import lombok.*;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppUserDTO implements Serializable {

    private long id;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private Date dateCreated;


}
