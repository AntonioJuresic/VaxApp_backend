package hr.tvz.juresic.vaxapp.sideeffect;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SideEffectRepository extends JpaRepository<SideEffect, Long> {
    List<SideEffect> findByVaccine_ResearchName(String researchName);
}
