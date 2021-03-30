package hr.tvz.juresic.vaxapp;

import java.util.List;
import java.util.Optional;

public interface VaccineRepository {
    List<Vaccine> findAll();

    Optional<Vaccine> findVaccineByResearchName(String researchName);

    Vaccine saveVaccine(Vaccine newVaccine);
    Vaccine updateVaccine(String researchName, Vaccine updatedVaccine);
    String deleteVaccine(String researchName);
}
