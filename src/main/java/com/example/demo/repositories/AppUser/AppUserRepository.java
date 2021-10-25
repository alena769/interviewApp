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

    @Query(value = "from AppUser where email LIKE %:email%", nativeQuery = true)
    Optional<AppUser> findByEmail(@Param("email") String email); // podle mailu

    List<AppUser> listAllAppUsers();

    @Query(value = "select AppUser from AppUser a where a.firstName like %:keyword% or a.lastName like %:keyword%", nativeQuery = true) //TODO dohledat jak je to spravne 
    List<AppUser> findByKeyword(@Param("keyword") String keyword);

//    @Query("FROM AppUser where firstName LIKE %:firstName%") // najit podle jmena
//    List<AppUser> findAllByFirstName(@Param("firstName") String firstName);
//
//    @Query("from AppUser where lastName LIKE %:lastName%")
//    List<AppUser> findAllByLastName(@Param("lastName") String lastName); // najit podle prijmeni

    AppUser findAllById(Long id); // najit podle id

    List<AppUser> findAllByIsActive(Boolean isActive);

    //@Query("select AppUser from AppUser group by AppUser.firstName, AppUser.lastName order by AppUser.dateCreated asc")
    // najit a seradit podle data vytvoreni; nemuzu najit reseni na problem s query ... tak to mam v UserServiceImpl

    void deleteById(Long id); // odstraneni uzivatele


}
