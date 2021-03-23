package hr.tvz.juresic.vaxapp;

import java.util.List;
import java.util.Optional;

public interface VaccineService {
    List<VaccineDTO> findAll();

    VaccineDTO findVaccineByResearchName(String researchName);
}
