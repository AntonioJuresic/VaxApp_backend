package hr.tvz.juresic.vaxapp;

import java.util.List;

public interface SideEffectService {
    List<SideEffectDTO> findAll();

    List<SideEffectDTO> findSideEffectByVaccineResearchName(String researchName);
    List<SideEffectDTO> findSideEffectByShortDescription(String shortDescription);
}
