package hr.tvz.juresic.vaxapp.vaccine;

import hr.tvz.juresic.vaxapp.sideeffect.SideEffectRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VaccineServiceImplementationTest {

    @Autowired
    VaccineServiceImplementation vaccineServiceImplementation;

    /*@MockBean
    VaccineRepository vaccineRepository;

    @MockBean
    SideEffectRepository sideEffectRepository;*/

    @Test
    void findAll() {
        List<VaccineDTO> vaccineDTOList = vaccineServiceImplementation.findAll();
        Assertions.assertNotNull(vaccineDTOList);
    }

    @Transactional
    @DirtiesContext
    @Test
    void findVaccineByResearchName() {
        VaccineDTO vaccineDTO = vaccineServiceImplementation.findVaccineByResearchName("BNT162b2");
        Assertions.assertNotNull(vaccineDTO);
    }

    /*
    @Transactional
    @DirtiesContext
    @Test
    void saveVaccine() {
    }

    @Test
    void updateVaccine() {
    }
    */

    @Test
    void deleteVaccine() {
        Integer removedVaccine = vaccineServiceImplementation.deleteVaccine("MRNA-1273");
        Assertions.assertEquals(removedVaccine, 1);
    }
}
