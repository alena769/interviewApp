package com.example.demo.repositories.AppUser;

import com.example.demo.models.AppUser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findById(Long id);

    @Query("from AppUser where email LIKE %:email%")
    Optional<AppUser> findByEmail(@Param("email") String email); // podle mailu

    @Query("from AppUser") // seznam useru
    List<AppUser> listAllAppUsers();

    @Query("FROM AppUser where firstName LIKE %:firstName%") // najit podle jmena
    List<AppUser> findAllByFirstName(@Param("firstName") String firstName);

    @Query("from AppUser where lastName LIKE %:lastName%")
    List<AppUser> findAllByLastName(@Param("lastName") String lastName); // najit podle prijmeni

    List<AppUser> findAllById(Long id); // najit podle id

    List<AppUser> findAllByIsActive(Boolean isActive); // TODO ohlidat si logiku, jestli nezmenit na string
                                                        // najit podle aktivovano/deaktivovano

    //@Query("select AppUser from AppUser group by AppUser.firstName, AppUser.lastName order by AppUser.dateCreated asc")
    // najit a seradit podle data vytvoreni; nemuzu najit reseni na problem s query ... tak to mam v UserServiceImpl

    void deleteById(Long id); // odstraneni uzivatele


}
