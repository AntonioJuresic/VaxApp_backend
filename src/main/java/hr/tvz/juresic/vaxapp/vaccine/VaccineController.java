package hr.tvz.juresic.vaxapp.vaccine;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/vaccine")
public class VaccineController {

    private final VaccineServiceImplementation vaccineServiceImplementation;

    public VaccineController(VaccineServiceImplementation vaccineServiceImplementation) {
        this.vaccineServiceImplementation = vaccineServiceImplementation;
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_CREATOR"})
    @GetMapping
    public ResponseEntity<List<VaccineDTO>> getAllVaccines(){
        return ResponseEntity.status(HttpStatus.OK).body(vaccineServiceImplementation.findAll());
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_CREATOR"})
    @GetMapping("/{researchName}")
    public ResponseEntity<VaccineDTO> getVaccineByResearchName(@PathVariable final String researchName) {
        VaccineDTO vaccineDTO = vaccineServiceImplementation.findVaccineByResearchName(researchName);

        if(vaccineDTO != null)
            return ResponseEntity.status(HttpStatus.OK).body(vaccineDTO);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Secured({"ROLE_CREATOR", "ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<VaccineDTO> addVaccine(@Valid @RequestBody final VaccineCommand vaccineCommand) {

        System.out.println("===================================================================================");

        VaccineDTO newVaccineDTO = vaccineServiceImplementation.saveVaccine(vaccineCommand);

        if(newVaccineDTO != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(newVaccineDTO);
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{researchName}")
    public ResponseEntity<VaccineDTO> updateVaccine(@PathVariable final String researchName, @Valid @RequestBody final VaccineCommand vaccineCommand) {
        VaccineDTO updatedVaccineDTO = vaccineServiceImplementation.updateVaccine(researchName, vaccineCommand);

        if(updatedVaccineDTO != null)
            return ResponseEntity.status(HttpStatus.OK).body(updatedVaccineDTO);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{vaccineResearchName}")
    public ResponseEntity deleteVaccine(@PathVariable final String vaccineResearchName){
        Integer deleteRows = vaccineServiceImplementation.deleteVaccine(vaccineResearchName);

        if(deleteRows != 0)
            return ResponseEntity.status(HttpStatus.OK).build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
