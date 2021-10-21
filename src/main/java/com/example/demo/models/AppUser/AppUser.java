package com.example.demo.models.AppUser;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AppUser implements Serializable, Comparable<AppUser> {

    private static final long serialLVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appUser_seq")
    @SequenceGenerator(name = "appUser_seq")
    private long id;

    private String firstName;
    private String lastName;
    private Boolean isActive;
    private String email;
    private String password;
    private String telephoneNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    public AppUser(String firstName, String lastName, String email, String password, String telephoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = false;
        this.email = email;
        this.password = password;
        this.telephoneNumber = telephoneNumber;
        this.dateCreated = new Date();
    }

    @Override // fce na porovnani timestamp dvou uzivatelu
    public int compareTo(AppUser o) {
        if(getDateCreated() == null || o.getDateCreated() == null) {

            return 0;
        }else

        return getDateCreated().compareTo(o.dateCreated);
    }
}
