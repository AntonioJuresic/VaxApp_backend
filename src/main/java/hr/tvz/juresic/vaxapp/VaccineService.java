package hr.tvz.juresic.vaxapp;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VaccineService {
    List<VaccineDTO> findAll();

    VaccineDTO findVaccineByResearchName(String researchName);

    VaccineDTO saveVaccine(VaccineCommand vaccineCommand);
    VaccineDTO updateVaccine(String researchName, VaccineCommand vaccineCommand);
    String deleteVaccine(String researchName);
}
