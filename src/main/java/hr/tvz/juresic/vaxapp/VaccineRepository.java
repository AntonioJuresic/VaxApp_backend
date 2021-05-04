package hr.tvz.juresic.vaxapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
    List<Vaccine> findByResearchName(String reserachName);

    @Transactional
    Integer removeByResearchName(String researchName);
}
