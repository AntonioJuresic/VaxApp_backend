package hr.tvz.juresic.vaxapp;

import java.util.List;

public interface VaccineService {
    List<VaccineDTO> findAll();

    List<VaccineDTO> findVaccineWithOneDose();
    List<VaccineDTO> findVaccineWithMultipleDoses();

    VaccineDTO findVaccineByResearchName(String researchName);
}
