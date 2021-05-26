package hr.tvz.juresic.vaxapp.sideeffect;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SideEffectRepositoryTest {

    @Autowired
    SideEffectRepository sideEffectRepository;

    @Test
    void findByVaccine_ResearchName() {
        List<SideEffect> sideEffectList = sideEffectRepository.findByVaccine_ResearchName("BNT162b2");

        Assertions.assertNotNull(sideEffectList);
        Assertions.assertEquals(sideEffectList.size(), 3);
    }
}
