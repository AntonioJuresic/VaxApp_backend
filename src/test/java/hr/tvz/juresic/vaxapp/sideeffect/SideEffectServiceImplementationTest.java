package hr.tvz.juresic.vaxapp.sideeffect;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SideEffectServiceImplementationTest {

    @Autowired
    SideEffectServiceImplementation sideEffectServiceImplementation;

    @Test
    void findAll() {
        List<SideEffectDTO> sideEffectDTOList = sideEffectServiceImplementation.findAll();

        Assertions.assertNotNull(sideEffectDTOList);
        Assertions.assertEquals(sideEffectDTOList.size(), 6);
    }

    @Test
    void findSideEffectByVaccineResearchName() {
        List<SideEffectDTO> sideEffectList = sideEffectServiceImplementation.findSideEffectByVaccineResearchName("BNT162b2");

        Assertions.assertNotNull(sideEffectList);
        Assertions.assertEquals(sideEffectList.size(), 3);
    }
}
