package hr.tvz.juresic.vaxapp.sideeffect;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("side-effect")
public class SideEffectController {

    private final SideEffectServiceImplementation sideEffectServiceImplementation;

    public SideEffectController(SideEffectServiceImplementation sideEffectServiceImplementation) {
        this.sideEffectServiceImplementation = sideEffectServiceImplementation;
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping()
    public ResponseEntity<List<SideEffectDTO>> getAllSideEffects(){
        return ResponseEntity.status(HttpStatus.OK).body(sideEffectServiceImplementation.findAll());
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping(params = "vaccineResearchName")
    public ResponseEntity<List<SideEffectDTO>> getSideEffectByResearchName(@RequestParam final String vaccineResearchName) {
        return ResponseEntity.status(HttpStatus.OK).body(sideEffectServiceImplementation.findSideEffectByVaccineResearchName(vaccineResearchName));
    }
}
