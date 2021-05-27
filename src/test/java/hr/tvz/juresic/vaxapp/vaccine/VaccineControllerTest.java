package hr.tvz.juresic.vaxapp.vaccine;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class VaccineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VaccineServiceImplementation vaccineServiceImplementation;

    @Test
    void getAllVaccines() throws Exception {
        when(vaccineServiceImplementation.findAll()).thenReturn(
                new ArrayList<VaccineDTO>(Arrays.asList(
                        new VaccineDTO(),
                        new VaccineDTO(),
                        new VaccineDTO()
                ))
        );

        mockMvc.perform(
                get("/vaccine")
                    .with(user("admin").password("password").roles("ADMIN"))
                    .with(csrf())
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)));
    }


    @Test
    void getAllVaccinesForbidden() throws Exception {
        when(vaccineServiceImplementation.findAll()).thenReturn(
                Collections.emptyList()
        );

        mockMvc.perform(
                get("/vaccine")
                        .with(user("BLABLA").password("BLABLA").roles("BLABLA"))
                        .with(csrf())
        )
                .andExpect(status().isForbidden());
    }

    @Test
    void getVaccineByResearchName() throws Exception {
        when(vaccineServiceImplementation.findVaccineByResearchName("BNT162b2")).thenReturn(
                new VaccineDTO()
        );

        mockMvc.perform(
                get("/vaccine/" + "BNT162b2")
                    .with(user("admin").password("password").roles("ADMIN"))
                    .with(csrf())
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getVaccineByResearchNameForbidden() throws Exception {
        when(vaccineServiceImplementation.findVaccineByResearchName("DOES EXIST")).thenReturn(
                new VaccineDTO()
        );

        mockMvc.perform(
                get("/vaccine/" + "BNT162b2")
                        .with(user("BLABLA").password("BLABLA").roles("BLABLA"))
                        .with(csrf())
        )
                .andExpect(status().isForbidden());
    }

    // Metoda je implementirana u pripremi
    @Test
    void getVaccineByResearchNameNotFound() throws Exception {
        when(vaccineServiceImplementation.findVaccineByResearchName("DOESN'T EXIST")).thenReturn(
                null
        );

        mockMvc.perform(
                get("/vaccine/" + "BLABLA")
                        .with(user("admin").password("password").roles("ADMIN"))
                        .with(csrf())
        )
                .andExpect(status().isNotFound());
    }

    @Transactional
    @DirtiesContext
    @Test
    void addVaccine() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        VaccineCommand vaccineCommand = new VaccineCommand(
                "TEST123",
                "TEST123",
                "MRNA",
                20,
                20);

        when(vaccineServiceImplementation.saveVaccine(vaccineCommand)).thenReturn(
             new VaccineDTO(
                     "TEST123",
                     "TEST123",
                     "MRNA",
                     20,
                     20
             )
        );

        mockMvc.perform(
                post("/vaccine")
                        .with(user("admin").password("password").roles("ADMIN"))
                        .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vaccineCommand))
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.researchName").value("TEST123"))
                .andExpect(jsonPath("$.manufacturerName").value("TEST123"))
                .andExpect(jsonPath("$.type").value("MRNA"))
                .andExpect(jsonPath("$.numberOfShots").value(20))
                .andExpect(jsonPath("$.availableDoses").value(20));
    }

    @Transactional
    @DirtiesContext
    @Test
    void addVaccineForbidden() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        VaccineCommand vaccineCommand = new VaccineCommand(
                "TEST123",
                "TEST123",
                "MRNA",
                20,
                20);

        when(vaccineServiceImplementation.saveVaccine(vaccineCommand)).thenReturn(
                new VaccineDTO(
                        "TEST123",
                        "TEST123",
                        "MRNA",
                        20,
                        20
                )
        );

        mockMvc.perform(
                post("/vaccine")
                        .with(user("BLABLA").password("BLABLA").roles("BLABLA"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vaccineCommand))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isForbidden());
    }

    @Transactional
    @DirtiesContext
    @Test
    void updateVaccine() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        VaccineCommand vaccineCommand = new VaccineCommand(
                "BNT162b2",
                "UPDATED123",
                "MRNA",
                40,
                40);

        when(vaccineServiceImplementation.updateVaccine("BNT162b2", vaccineCommand)).thenReturn(
                new VaccineDTO(
                        "BNT162b2",
                        "UPDATED123",
                        "MRNA",
                        40,
                        40
                )
        );

        mockMvc.perform(
                put("/vaccine/" +  "BNT162b2")
                        .with(user("admin").password("password").roles("ADMIN"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vaccineCommand))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.researchName").value("BNT162b2"))
                .andExpect(jsonPath("$.manufacturerName").value("UPDATED123"))
                .andExpect(jsonPath("$.type").value("MRNA"))
                .andExpect(jsonPath("$.numberOfShots").value(40))
                .andExpect(jsonPath("$.availableDoses").value(40));
    }

    @Transactional
    @DirtiesContext
    @Test
    void updateVaccineForbidden() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        VaccineCommand vaccineCommand = new VaccineCommand(
                "BNT162b2",
                "UPDATED123",
                "MRNA",
                40,
                40);

        when(vaccineServiceImplementation.updateVaccine("BNT162b2", vaccineCommand)).thenReturn(
                new VaccineDTO(
                        "BNT162b2",
                        "UPDATED123",
                        "MRNA",
                        40,
                        40
                )
        );

        mockMvc.perform(
                put("/vaccine/" +  "BNT162b2")
                        .with(user("BLABLA").password("BLABLA").roles("BLABLA"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vaccineCommand))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isForbidden());
    }

    @Transactional
    @DirtiesContext
    @Test
    void updateVaccineNotFound() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        VaccineCommand vaccineCommand = new VaccineCommand(
                "BNT162b2",
                "UPDATED123",
                "MRNA",
                40,
                40);

        when(vaccineServiceImplementation.updateVaccine("BNT162b2", vaccineCommand)).thenReturn(
                new VaccineDTO(
                        "BNT162b2",
                        "UPDATED123",
                        "MRNA",
                        40,
                        40
                )
        );

        mockMvc.perform(
                put("/vaccine/" +  "BLABLA")
                        .with(user("admin").password("password").roles("ADMIN"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vaccineCommand))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNotFound());
    }

    @Transactional
    @DirtiesContext
    @Test
    void deleteVaccine() throws Exception {
        when(vaccineServiceImplementation.deleteVaccine("BNT162b2")).thenReturn(
                new Integer(1)
        );

        mockMvc.perform(
                delete("/vaccine/" + "BNT162b2")
                        .with(user("admin").password("password").roles("ADMIN"))
                        .with(csrf())
        )
                .andExpect(status().isOk());
    }

    @Transactional
    @DirtiesContext
    @Test
    void deleteVaccineForbidden() throws Exception {
        when(vaccineServiceImplementation.deleteVaccine("BNT162b2")).thenReturn(
                new Integer(1)
        );

        mockMvc.perform(
                delete("/vaccine/" + "BNT162b2")
                        .with(user("BLABLA").password("BLABLA").roles("BLABLA"))
                        .with(csrf())
        )
                .andExpect(status().isForbidden());
    }

    @Transactional
    @DirtiesContext
    @Test
    void deleteVaccineNotFound() throws Exception {
        when(vaccineServiceImplementation.deleteVaccine("BLABLA")).thenReturn(
                new Integer(0)
        );

        mockMvc.perform(
                delete("/vaccine/" + "BLABLA")
                        .with(user("admin").password("password").roles("ADMIN"))
                        .with(csrf())
        )
                .andExpect(status().isNotFound());
    }
}
