package hr.tvz.juresic.vaxapp.vaccine;

import hr.tvz.juresic.vaxapp.sideeffect.SideEffect;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VaccineRepositoryTest {

    @Autowired
    VaccineRepository vaccineRepository;

    @Test
    void findByResearchName() {
        Vaccine vaccine = vaccineRepository.findByResearchName("BNT162b2");

        Assertions.assertNotNull(vaccine);
    }

    @Transactional
    @DirtiesContext
    @Test
    void removeByResearchName() {
        Integer removedVaccine = vaccineRepository.removeByResearchName("BNT162b2");

        System.out.println(removedVaccine);

        Assertions.assertEquals(removedVaccine, 1);
        //Vaccine vaccine = vaccineRepository.findByResearchName("BNT162b2");

        //Assertions.assertNull(vaccine);
    }
}
