package hr.tvz.juresic.vaxapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("sideeffect")
public class SideEffectController {

    private final SideEffectServiceImplementation sideEffectServiceImplementation;

    public SideEffectController(SideEffectServiceImplementation sideEffectServiceImplementation) {
        this.sideEffectServiceImplementation = sideEffectServiceImplementation;
    }

    @GetMapping
    public ResponseEntity<List<SideEffectDTO>> getAllSideEffects(){
        return ResponseEntity.status(HttpStatus.OK).body(sideEffectServiceImplementation.findAll());
    }

    @GetMapping("/{researchName}")
    public ResponseEntity<SideEffectDTO> getVaccineByResearchName(@PathVariable final String researchName) {
        SideEffectDTO sideEffectDTO = sideEffectServiceImplementation.findSideEffectByVaccineResearchName(researchName);

        if(sideEffectDTO != null)
            return ResponseEntity.status(HttpStatus.OK).body(sideEffectDTO);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
