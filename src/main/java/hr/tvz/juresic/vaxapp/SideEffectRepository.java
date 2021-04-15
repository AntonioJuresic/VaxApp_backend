package hr.tvz.juresic.vaxapp;

import java.util.List;
import java.util.Optional;

public interface SideEffectRepository {
    List<SideEffect> findAll();
    Optional<SideEffect> findSideEffectByShortDescription(String shortDescription);
}
