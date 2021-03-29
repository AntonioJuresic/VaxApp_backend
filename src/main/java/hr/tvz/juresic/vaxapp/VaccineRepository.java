package hr.tvz.juresic.vaxapp;

import java.util.List;
import java.util.Optional;

public interface VaccineRepository {
    List<Vaccine> findAll();

    Optional<Vaccine> findVaccineByResearchName(String researchName);

    String saveVaccine(Vaccine newVaccine);
}
