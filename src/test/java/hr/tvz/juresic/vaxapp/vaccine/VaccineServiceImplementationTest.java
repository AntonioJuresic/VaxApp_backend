package hr.tvz.juresic.vaxapp.vaccine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.*;


@SpringBootTest
class VaccineServiceImplementationTest {

    @Autowired
    VaccineService vaccineService;

    @MockBean
    VaccineRepository vaccineRepository;

    @Test
    void findAll() {
        when(vaccineRepository.findAll()).thenReturn(
                new ArrayList<Vaccine>(Arrays.asList(
                        new Vaccine("test1", "test1", VaccineType.MRNA, 10, 10),
                        new Vaccine("test2", "test2", VaccineType.MRNA, 10, 10),
                        new Vaccine("test2", "test2", VaccineType.MRNA, 10, 10)
                ))
        );

        Assertions.assertNotNull(vaccineService.findAll());
        Assertions.assertEquals(vaccineService.findAll().size(), 3);
    }

    @Test
    void findVaccineByResearchName() {
        when(vaccineRepository.findByResearchName(matches("TRUE"))).thenReturn(
                new Vaccine("TRUE", "TRUE", VaccineType.MRNA, 10, 10)
        );

        when(vaccineRepository.findByResearchName(matches("FALSE"))).thenReturn(
                null
        );

        Assertions.assertNotNull(vaccineService.findVaccineByResearchName("TRUE"));
        Assertions.assertNull(vaccineService.findVaccineByResearchName("FALSE"));
    }


    @Transactional
    @DirtiesContext
    @Test
    void saveVaccine() {
        VaccineCommand command = new VaccineCommand(
                "SAVED",
                "SAVED",
                "MRNA",
                10,
                10
        );

        Vaccine vaccine = new Vaccine(
                command.getResearchName(),
                command.getManufacturerName(),
                VaccineType.valueOf(command.getType()),
                command.getNumberOfShots(),
                command.getAvailableDoses()
        );

        when(vaccineRepository.saveAndFlush(vaccine)).thenReturn(
                new Vaccine("SAVED", "SAVED", VaccineType.MRNA, 10, 10)
        );

        Assertions.assertNotNull(vaccineRepository.saveAndFlush(vaccine));
    }

    @Transactional
    @DirtiesContext
    @Test
    void updateVaccine() {
        String researchName = "UPDATE";
        VaccineCommand command = new VaccineCommand(
                "UPDATE",
                "UPDATE",
                "MRNA",
                10,
                10
        );

        Vaccine vaccine = new Vaccine(
                command.getResearchName(),
                command.getManufacturerName(),
                VaccineType.valueOf(command.getType()),
                command.getNumberOfShots(),
                command.getAvailableDoses()
        );

        when(vaccineRepository.findByResearchName(matches("UPDATE"))).thenReturn(
                new Vaccine("UPDATE", "UPDATE", VaccineType.MRNA, 10, 10)
        );

        when(vaccineRepository.findByResearchName(matches("FALSE"))).thenReturn(
                null
        );

        when(vaccineRepository.saveAndFlush(vaccine)).thenReturn(
                new Vaccine("UPDATED", "UPDATED", VaccineType.MRNA, 10, 10)
        );

        Assertions.assertNotNull(vaccineService.findVaccineByResearchName("UPDATE"));
        Assertions.assertNull(vaccineService.findVaccineByResearchName("FALSE"));

        Assertions.assertNotNull(vaccineRepository.saveAndFlush(vaccine));
    }

    @Transactional
    @DirtiesContext
    @Test
    void deleteVaccine() {
        /*when(vaccineRepository.removeByResearchName("TRUE")).thenReturn(
                new Integer(1)
        );

        when(vaccineRepository.removeByResearchName("FALSE")).thenReturn(
                new Integer(0)
        );

        Assertions.assertEquals(vaccineService.findVaccineByResearchName("TRUE"), new Integer(1));
        Assertions.assertEquals(vaccineService.findVaccineByResearchName("FALSE"), new Integer(0));*/

        vaccineService.deleteVaccine("researchName");
        verify(vaccineRepository).removeByResearchName("researchName");
    }
}
