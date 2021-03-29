package hr.tvz.juresic.vaxapp;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VaccineServiceImplementation implements VaccineService{

    private final VaccineRepositoryImplementation vaccineRepositoryImplementation;

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
    public String saveVaccine(VaccineCommand vaccineCommand) {
        Vaccine newVaccine = new Vaccine();

        newVaccine.setResearchName(vaccineCommand.getResearchName());
        newVaccine.setManufacturerName(vaccineCommand.getManufacturerName());
        newVaccine.setVaccineType(Vaccine.VaccineType.valueOf(vaccineCommand.getVaccineType()));
        newVaccine.setNumberOfDoses(vaccineCommand.getNumberOfDoses());
        newVaccine.setAvailableDoses(vaccineCommand.getAvailableDoses());

        return vaccineRepositoryImplementation.saveVaccine(newVaccine);
    }

    private VaccineDTO mapVaccinesToDTO(final Vaccine vaccine) {
        return new VaccineDTO(vaccine.getManufacturerName(), vaccine.getAvailableDoses());
    }
}
