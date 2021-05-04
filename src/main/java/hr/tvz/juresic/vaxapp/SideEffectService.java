package hr.tvz.juresic.vaxapp;

import java.util.List;

public interface SideEffectService {
    List<SideEffectDTO> findAll();

    SideEffectDTO findSideEffectByVaccineResearchName(String researchName);
}
