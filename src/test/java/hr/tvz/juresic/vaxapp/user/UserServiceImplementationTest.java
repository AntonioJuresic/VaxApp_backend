package hr.tvz.juresic.vaxapp.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplementationTest {

    @Autowired
    UserServiceImplementation userServiceImplementation;

    @MockBean
    UserRepository userRepository;

    @Test
    void findUserByUsername() {
        when(userRepository.findOneByUsername(matches("DOESEXIST"))).thenReturn(
                java.util.Optional.of(new User())
        );

        when(userRepository.findOneByUsername(matches("DOESNNOTEXIST"))).thenReturn(
                null
        );

        Assertions.assertNotNull(userRepository.findOneByUsername("DOESEXIST"));
        Assertions.assertNull(userRepository.findOneByUsername("DOESNNOTEXIST"));
    }
}
