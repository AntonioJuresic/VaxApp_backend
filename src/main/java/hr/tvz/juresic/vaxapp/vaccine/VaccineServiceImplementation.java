package hr.tvz.juresic.vaxapp.vaccine;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VaccineServiceImplementation implements VaccineService{

    private final VaccineRepository vaccineRepository;

    public VaccineServiceImplementation(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }

    @Override
    public List<VaccineDTO> findAll() {
        return vaccineRepository.findAll().stream().map(this::mapVaccinesToDTO).collect(Collectors.toList());
    }

    @Override
    public VaccineDTO findVaccineByResearchName(String researchName) {
        Vaccine vaccine = vaccineRepository.findByResearchName(researchName);

        if (vaccine != null) {
            return mapVaccinesToDTO(vaccine);
        }

        return null;

        //return vaccineRepository.findByResearchName(researchName).stream().findFirst().map(this::mapVaccinesToDTO).orElse(null);
    }

    @Override
    public VaccineDTO saveVaccine(VaccineCommand vaccineCommand) {
        Vaccine newVaccine = new Vaccine();

        newVaccine.setResearchName(vaccineCommand.getResearchName());
        newVaccine.setManufacturerName(vaccineCommand.getManufacturerName());
        newVaccine.setVaccineType(VaccineType.valueOf(vaccineCommand.getType()));
        newVaccine.setNumberOfShots(vaccineCommand.getNumberOfShots());
        newVaccine.setAvailableDoses(vaccineCommand.getAvailableDoses());

        Vaccine newlyAddedVaccine = vaccineRepository.saveAndFlush(newVaccine);

        System.out.println(newlyAddedVaccine.toString());

        return mapVaccinesToDTO(newlyAddedVaccine);
    }

    @Override
    public VaccineDTO updateVaccine(String researchName, VaccineCommand vaccineCommand) {
        //Riješenje preuzeto s interneta
        //https://www.baeldung.com/spring-data-partial-update

        Vaccine updatedVaccine = vaccineRepository.findByResearchName(researchName);

        if(updatedVaccine != null) {
            updatedVaccine.setResearchName(researchName);
            updatedVaccine.setManufacturerName(vaccineCommand.getManufacturerName());
            updatedVaccine.setVaccineType(VaccineType.valueOf(vaccineCommand.getType()));
            updatedVaccine.setNumberOfShots(vaccineCommand.getNumberOfShots());
            updatedVaccine.setAvailableDoses(vaccineCommand.getAvailableDoses());

            return mapVaccinesToDTO(vaccineRepository.saveAndFlush(updatedVaccine));

        }

        return null;
    }

    @Override
    public Integer deleteVaccine(String researchName) {
        return vaccineRepository.removeByResearchName(researchName);
    }

    private VaccineDTO mapVaccinesToDTO(final Vaccine vaccine) {
        return new VaccineDTO(
                vaccine.getResearchName(),
                vaccine.getManufacturerName(),
                vaccine.getVaccineType().toString(),
                vaccine.getNumberOfShots(),
                vaccine.getAvailableDoses()
        );
    }
}
