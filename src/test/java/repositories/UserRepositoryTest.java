package repositories;

import com.example.demo.repositories.AppUser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

//TODO dodelat testy

@AllArgsConstructor
class UserRepositoryTest {
    private final AppUserRepository userRepository;

    @Test
    @Transactional
    void whenFindAllByFirstName_thenFindShouldBeSuccessful() {

    }

    @Test
    @Transactional
    void whenFindAllByLastName_thenFindShouldBeSuccessful() {
    }

}