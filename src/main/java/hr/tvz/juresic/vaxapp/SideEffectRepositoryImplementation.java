package hr.tvz.juresic.vaxapp;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class SideEffectRepositoryImplementation implements SideEffectRepository{
    private List<SideEffect> mockedSideEffects = new ArrayList<>(Arrays.asList(
            new SideEffect("Crvenilo", 5, "Crvenilo kod mjesta uboda"),
            new SideEffect("Vrucica", 20, "Vrucica kod pacijenta"),
            new SideEffect("Zelenica", 5, "Zelenica kod mjesta uboda"),
            new SideEffect("Hladnica", 69, "Hladnica kod pacijenta"),
            new SideEffect("Markica", 52, "Crvenilo kod mjesta pošte") ));

    @Override
    public List<SideEffect> findAll() { return mockedSideEffects; }

    @Override
    public Optional<SideEffect> findSideEffectByShortDescription(String shortDescription) {
        return mockedSideEffects.stream().filter(se -> Objects.equals(se.getShortDescription(), shortDescription)).findAny();
    }
}
