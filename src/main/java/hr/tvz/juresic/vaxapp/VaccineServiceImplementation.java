package hr.tvz.juresic.vaxapp;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VaccineServiceImplementation implements VaccineService{

    private final VaccineRepository vaccineRepositoryImplementation;

    public VaccineServiceImplementation(VaccineRepositoryImplementation vaccineRepositoryImplementation) {
        this.vaccineRepositoryImplementation = vaccineRepositoryImplementation;
    }

    @Override
    public List<VaccineDTO> findAll() {
        return vaccineRepositoryImplementation.findAll().stream().map(this::mapVaccinesToDTO).collect(Collectors.toList());
    }

    @Override
    public VaccineDTO findVaccineByResearchName(String researchName) {
        return vaccineRepositoryImplementation.findVaccineByResearchName(researchName).map(this::mapVaccinesToDTO).orElse(null);
    }

    @Override
    public List<VaccineDTO> findVaccineWithOneDose() {
        return vaccineRepositoryImplementation.findVaccineWithOneDose().stream().map(this::mapVaccinesToDTO).collect(Collectors.toList());
    }

    @Override
    public List<VaccineDTO> findVaccineWithMultipleDoses() {
        return vaccineRepositoryImplementation.findVaccineWithMultipleDoses().stream().map(this::mapVaccinesToDTO).collect(Collectors.toList());
    }

    private VaccineDTO mapVaccinesToDTO(final Vaccine vaccine) {
        return new VaccineDTO(vaccine.getManufacturerName(), vaccine.getNumberOfDoses());
    }
}
