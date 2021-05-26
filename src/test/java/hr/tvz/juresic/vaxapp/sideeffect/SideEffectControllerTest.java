package hr.tvz.juresic.vaxapp.sideeffect;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SideEffectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SideEffectServiceImplementation sideEffectServiceImplementation;

    @Test
    void getAllSideEffects() throws Exception {
        when(sideEffectServiceImplementation.findAll()).thenReturn(
                new ArrayList<SideEffectDTO>(Arrays.asList(
                        new SideEffectDTO(),
                        new SideEffectDTO(),
                        new SideEffectDTO()
                ))
        );

        mockMvc.perform(
                get("/side-effect")
                        .with(user("admin").password("password").roles("ADMIN"))
                        .with(csrf())
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)));
    }


    @Test
    void getAllSideEffectsForbidden() throws Exception {
        when(sideEffectServiceImplementation.findAll()).thenReturn(
                new ArrayList<SideEffectDTO>(Arrays.asList(
                        new SideEffectDTO(),
                        new SideEffectDTO(),
                        new SideEffectDTO()
                ))
        );

        mockMvc.perform(
                get("/side-effect")
                        .with(user("BLABLA").password("BLABLA").roles("BLABLA"))
                        .with(csrf())
        )
                .andExpect(status().isForbidden());
    }

    @Test
    void getSideEffectByResearchName() throws Exception {
        when(sideEffectServiceImplementation.findSideEffectByVaccineResearchName(any())).thenReturn(
                new ArrayList<SideEffectDTO>(Arrays.asList(
                        new SideEffectDTO(),
                        new SideEffectDTO(),
                        new SideEffectDTO()
                ))
        );

        mockMvc.perform(
                get("/side-effect?vaccineResearchName=" + "BNT162b2")
                    .with(user("admin").password("password").roles("ADMIN"))
                .with(csrf())
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void getSideEffectByResearchNameForbidden() throws Exception {
        when(sideEffectServiceImplementation.findSideEffectByVaccineResearchName(any())).thenReturn(
                new ArrayList<SideEffectDTO>(Arrays.asList(
                        new SideEffectDTO(),
                        new SideEffectDTO(),
                        new SideEffectDTO()
                ))
        );

        mockMvc.perform(
                get("/side-effect?vaccineResearchName=" + "BNT162b2")
                        .with(user("BLABLA").password("BLABLA").roles("BLABLA"))
                        .with(csrf())
        )
                .andExpect(status().isForbidden());
    }

    @Test
    void getSideEffectByResearchNameNotFound() throws Exception {
        when(sideEffectServiceImplementation.findSideEffectByVaccineResearchName(any())).thenReturn(
                Collections.emptyList()
        );

        mockMvc.perform(
                get("/side-effect?vaccineResearchName=" + "FALSE")
                        .with(user("admin").password("password").roles("ADMIN"))
                        .with(csrf())
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
