package hr.tvz.juresic.vaxapp.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void findOneByUsername() {
        Assertions.assertNotNull(userRepository.findOneByUsername("admin"));
        Assertions.assertTrue(userRepository.findOneByUsername("EMPTY").isEmpty());
    }
}
