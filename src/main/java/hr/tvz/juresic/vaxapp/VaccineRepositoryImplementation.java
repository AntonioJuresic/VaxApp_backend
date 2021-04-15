package hr.tvz.juresic.vaxapp;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class VaccineRepositoryImplementation implements VaccineRepository {
    private List<Vaccine> mockedVaccines = new ArrayList<>(Arrays.asList(
            new Vaccine("AZD1222", "Astra Zeneca", "VIRAL_VECTOR", 1, 2000),
            new Vaccine("BNT162b2", "Pfizer-BioNTech", "RNA", 2, 42000),
            new Vaccine("mRNA-1273", "Moderna", "RNA", 2, 50000),
            new Vaccine("JNJ-78436735", "Johnson & Johnson", "VIRAL_VECTOR", 3, 40000),
            new Vaccine("BBIBP-CorV", "Sinopharm", "VIRAL_VECTOR", 2, 40000)));

    @Override
    public List<Vaccine> findAll() { return mockedVaccines; }

    @Override
    public Optional<Vaccine> findVaccineByResearchName(final String researchName) {
        return mockedVaccines.stream().filter(v -> Objects.equals(v.getResearchName(), researchName)).findAny();
    }

    @Override
    public Vaccine saveVaccine(Vaccine newVaccine) {
        ListIterator<Vaccine> iterator = mockedVaccines.listIterator();

        while (iterator.hasNext()) {
            Vaccine currentVaccine = iterator.next();

            if (currentVaccine.getResearchName().equals(newVaccine.getResearchName())) {
                return null;
            }
        }

        mockedVaccines.add(newVaccine);
        return newVaccine;
    }

    @Override
    public Vaccine updateVaccine(String researchName, Vaccine updatedVaccine) {
        ListIterator<Vaccine> iterator = mockedVaccines.listIterator();

        while (iterator.hasNext()) {
            Vaccine currentVaccine = iterator.next();

            if (currentVaccine.getResearchName().equals(researchName)) {
                iterator.set(updatedVaccine);
                return updatedVaccine;
            }
        }

        return null;
    }

    @Override
    public String deleteVaccine(String researchName) {
        ListIterator<Vaccine> iterator = mockedVaccines.listIterator();

        while (iterator.hasNext()) {
            Vaccine currentVaccine = iterator.next();

            if (currentVaccine.getResearchName().equals(researchName)) {
                iterator.remove();
                return "success";
            }
        }

        return null;
    }
}
