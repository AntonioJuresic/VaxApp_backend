package hr.tvz.juresic.vaxapp;

import java.util.List;
import java.util.Optional;

public interface VaccineRepository {
    List<Vaccine> findAll();
    Vaccine saveVaccine(Vaccine newVaccine);
    Vaccine updateVaccine(String researchName, Vaccine updatedVaccine);

    Optional<Vaccine>findVaccineByResearchName(String researchName);
    Integer deleteVaccine(String researchName);
}
