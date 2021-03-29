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
    public VaccineDTO saveVaccine(VaccineCommand vaccineCommand) {
        Vaccine newVaccine = new Vaccine();

        newVaccine.setResearchName(vaccineCommand.getResearchName());
        newVaccine.setManufacturerName(vaccineCommand.getManufacturerName());
        newVaccine.setVaccineType(Vaccine.VaccineType.valueOf(vaccineCommand.getVaccineType()));
        newVaccine.setNumberOfDoses(vaccineCommand.getNumberOfDoses());
        newVaccine.setAvailableDoses(vaccineCommand.getAvailableDoses());

        if (vaccineRepositoryImplementation.saveVaccine(newVaccine) != null) {
            return new VaccineDTO(newVaccine.getManufacturerName(), newVaccine.getAvailableDoses());
        }

        return null;
    }

    @Override
    public VaccineDTO updateVaccine(String researchName, VaccineCommand vaccineCommand) {
        Vaccine updatedVaccine = new Vaccine();

        updatedVaccine.setResearchName(researchName);
        updatedVaccine.setManufacturerName(vaccineCommand.getManufacturerName());
        updatedVaccine.setVaccineType(Vaccine.VaccineType.valueOf(vaccineCommand.getVaccineType()));
        updatedVaccine.setNumberOfDoses(vaccineCommand.getNumberOfDoses());
        updatedVaccine.setAvailableDoses(vaccineCommand.getAvailableDoses());

        if (vaccineRepositoryImplementation.updateVaccine(researchName, updatedVaccine) != null) {
            return new VaccineDTO(updatedVaccine.getManufacturerName(), updatedVaccine.getAvailableDoses());
        }

        return null;
    }

    private VaccineDTO mapVaccinesToDTO(final Vaccine vaccine) {
        return new VaccineDTO(vaccine.getManufacturerName(), vaccine.getAvailableDoses());
    }
}
