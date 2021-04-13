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

        Vaccine newlyAddedVaccine = vaccineRepositoryImplementation.saveVaccine(newVaccine);

        if (newlyAddedVaccine != null) {
            return mapVaccinesToDTO(newlyAddedVaccine);
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

        Vaccine newlyUpdateVaccine = vaccineRepositoryImplementation.updateVaccine(researchName, updatedVaccine);

        if (newlyUpdateVaccine != null) {
            return mapVaccinesToDTO(newlyUpdateVaccine);
        }

        return null;
    }

    @Override
    public String deleteVaccine(String researchName) {
        return vaccineRepositoryImplementation.deleteVaccine(researchName);
    }

    private VaccineDTO mapVaccinesToDTO(final Vaccine vaccine) {
        return new VaccineDTO(vaccine.getResearchName(), vaccine.getManufacturerName(), vaccine.getAvailableDoses());
    }
}
