package hr.tvz.juresic.vaxapp;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SideEffectServiceImplementation implements SideEffectService{

    private final SideEffectRepositoryImplementation sideEffectRepositoryImplementation;

    public SideEffectServiceImplementation(SideEffectRepositoryImplementation sideEffectRepositoryImplementation) {
        this.sideEffectRepositoryImplementation = sideEffectRepositoryImplementation;
    }

    @Override
    public List<SideEffectDTO> findAll() {
        return sideEffectRepositoryImplementation.findAll().stream().map(this::mapSideEffectsToDTO).collect(Collectors.toList());
    }

    @Override
    public SideEffectDTO findSideEffectServiceByShortDescription(String shortDescription) {
        return sideEffectRepositoryImplementation.findSideEffectByShortDescription(shortDescription).map(this::mapSideEffectsToDTO).orElse(null);
    }


    private SideEffectDTO mapSideEffectsToDTO(final SideEffect sideEffect) {
        return new SideEffectDTO(sideEffect.getShortDescription(), sideEffect.getFrequency(), sideEffect.getLongDescription());
    }


}
