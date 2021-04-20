package hr.tvz.juresic.vaxapp;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VaccineServiceImplementation implements VaccineService{

    private final JDBCVaccineRepository jDBCVaccineRepository;

    public VaccineServiceImplementation(JDBCVaccineRepository jDBCVaccineRepository) {
        this.jDBCVaccineRepository = jDBCVaccineRepository;
    }

    @Override
    public List<VaccineDTO> findAll() {
        return jDBCVaccineRepository.findAll().stream().map(this::mapVaccinesToDTO).collect(Collectors.toList());
    }

    @Override
    public VaccineDTO findVaccineByResearchName(String researchName) {
        return jDBCVaccineRepository.findVaccineByResearchName(researchName).map(this::mapVaccinesToDTO).orElse(null);
    }

    @Override
    public VaccineDTO saveVaccine(VaccineCommand vaccineCommand) {
        Vaccine newVaccine = new Vaccine();

        newVaccine.setResearchName(vaccineCommand.getResearchName());
        newVaccine.setManufacturerName(vaccineCommand.getManufacturerName());
        newVaccine.setVaccineType(Vaccine.VaccineType.valueOf(vaccineCommand.getVaccineType()));
        newVaccine.setNumberOfDoses(vaccineCommand.getNumberOfDoses());
        newVaccine.setAvailableDoses(vaccineCommand.getAvailableDoses());

        Vaccine newlyAddedVaccine = jDBCVaccineRepository.saveVaccine(newVaccine);

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

        Vaccine newlyUpdateVaccine = jDBCVaccineRepository.updateVaccine(researchName, updatedVaccine);

        if (newlyUpdateVaccine != null) {
            return mapVaccinesToDTO(newlyUpdateVaccine);
        }

        return null;
    }

    @Override
    public Integer deleteVaccine(String researchName) {
        return jDBCVaccineRepository.deleteVaccine(researchName);
    }

    private VaccineDTO mapVaccinesToDTO(final Vaccine vaccine) {
        return new VaccineDTO(vaccine.getResearchName(), vaccine.getManufacturerName(), vaccine.getAvailableDoses());
    }
}
