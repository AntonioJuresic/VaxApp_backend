package hr.tvz.juresic.vaxapp.sideeffect;

import java.util.List;

public interface SideEffectService {
    List<SideEffectDTO> findAll();

    List<SideEffectDTO> findSideEffectByVaccineResearchName(String researchName);
}
