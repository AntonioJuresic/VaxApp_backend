package hr.tvz.juresic.vaxapp;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SideEffectService {
    List<SideEffectDTO> findAll();

    SideEffectDTO findSideEffectServiceByShortDescription(String shortDescription);
}
