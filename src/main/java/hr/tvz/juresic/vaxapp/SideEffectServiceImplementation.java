package hr.tvz.juresic.vaxapp;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SideEffectServiceImplementation implements SideEffectService{

    private final SideEffectRepository sideEffectRepository;

    public SideEffectServiceImplementation(SideEffectRepository sideEffectRepository) {
        this.sideEffectRepository = sideEffectRepository;
    }

    @Override
    public List<SideEffectDTO> findAll() {
        return sideEffectRepository.findAll().stream().map(this::mapSideEffectsToDTO).collect(Collectors.toList());
    }

    @Override
    public List<SideEffectDTO> findSideEffectByVaccineResearchName(String researchName) {
        return sideEffectRepository.findByVaccine_ResearchName(researchName).stream().map(this::mapSideEffectsToDTO).collect(Collectors.toList());
    }

    private SideEffectDTO mapSideEffectsToDTO(final SideEffect sideEffect) {
        return new SideEffectDTO(sideEffect.getShortDescription(), sideEffect.getLongDescription(), sideEffect.getFrequency());
    }
}
