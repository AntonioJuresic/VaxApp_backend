package hr.tvz.juresic.vaxapp;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VaccineRepositoryImplementation implements VaccineRepository{

    private final List<Vaccine> MOCKED_VACCINES = Arrays.asList(
            new Vaccine("Sinovac", "China", Vaccine.VaccineType.RNA, 2, 20),
            new Vaccine("Sputnik", "Russia", Vaccine.VaccineType.RNA, 4, 10),
            new Vaccine("Moderna", "France", Vaccine.VaccineType.RNA, 1, 40)
    );

    @Override
    public List<Vaccine> findAll() {
        return MOCKED_VACCINES;
    }

    @Override
    public Optional<Vaccine> findVaccineByResearchName(final String researchName) {
        return MOCKED_VACCINES.stream().filter(v -> Objects.equals(v.getResearchName(), researchName)).findAny();
    }
}
