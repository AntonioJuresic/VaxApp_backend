package hr.tvz.juresic.vaxapp.sideeffect;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.when;

@SpringBootTest
class SideEffectServiceImplementationTest {

    @Autowired
    SideEffectServiceImplementation sideEffectServiceImplementation;

    @MockBean
    SideEffectRepository sideEffectRepository;

    @Test
    void findAll() {
        when(sideEffectRepository.findAll()).thenReturn(
                new ArrayList<SideEffect>(Arrays.asList(
                        new SideEffect(),
                        new SideEffect(),
                        new SideEffect()
                ))
        );

        Assertions.assertNotNull(sideEffectRepository.findAll());
        Assertions.assertEquals(sideEffectRepository.findAll().size(), 3);
    }

    @Test
    void findSideEffectByVaccineResearchName() {
        when(sideEffectRepository.findByVaccine_ResearchName(matches("DOESEXIST"))).thenReturn(
                new ArrayList<SideEffect>(Arrays.asList(
                        new SideEffect(),
                        new SideEffect(),
                        new SideEffect()
                ))
        );

        when(sideEffectRepository.findByVaccine_ResearchName(matches("DOESNNOTEXIST"))).thenReturn(
                null
        );

        Assertions.assertNotNull(sideEffectRepository.findByVaccine_ResearchName("DOESEXIST"));
        Assertions.assertNull(sideEffectRepository.findByVaccine_ResearchName("DOESNNOTEXIST"));
    }
}
