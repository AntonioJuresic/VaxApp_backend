package hr.tvz.juresic.vaxapp;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VaccineService {
    List<VaccineDTO> findAll();

    VaccineDTO findVaccineByResearchName(String researchName);

    String saveVaccine(VaccineCommand vaccineCommand);
}
