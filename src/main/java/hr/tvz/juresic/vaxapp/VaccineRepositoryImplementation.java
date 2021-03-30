package hr.tvz.juresic.vaxapp;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class VaccineRepositoryImplementation implements VaccineRepository {

    private List<Vaccine> mockedVaccines = new ArrayList<Vaccine>();

    public VaccineRepositoryImplementation() {
        mockedVaccines.add(new Vaccine("Sinovac", "China", Vaccine.VaccineType.RNA, 2, 20));
        mockedVaccines.add(new Vaccine("Sputnik", "Russia", Vaccine.VaccineType.RNA, 4, 10));
        mockedVaccines.add(new Vaccine("Moderna", "France", Vaccine.VaccineType.RNA, 1, 40));
    }

    @Override
    public List<Vaccine> findAll() {
        return mockedVaccines;
    }

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

        /*if (mockedVaccines.contains(newVaccine)) {
            return null;
        } else {
            mockedVaccines.add(newVaccine);
            return newVaccine;
        }*/
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
