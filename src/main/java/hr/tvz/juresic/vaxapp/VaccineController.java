package hr.tvz.juresic.vaxapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("vaccine")
public class VaccineController {

    private final VaccineServiceImplementation vaccineServiceImplementation;

    public VaccineController(VaccineServiceImplementation vaccineServiceImplementation) {
        this.vaccineServiceImplementation = vaccineServiceImplementation;
    }

    @GetMapping
    public List<VaccineDTO> getAllVaccines(){
        return vaccineServiceImplementation.findAll();
    }

    @GetMapping("/{researchName}")
    public VaccineDTO getVaccineByResearchName(@PathVariable final String researchName) {
        return vaccineServiceImplementation.findVaccineByResearchName(researchName);
    }

    @PostMapping
    public ResponseEntity<String> addVaccine(@Valid @RequestBody final VaccineCommand vaccineCommand) {
        String status = vaccineServiceImplementation.saveVaccine(vaccineCommand);

        if(status.equals("New Vaccine added")) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("New Vaccine added");
        } else {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Vaccine already exists");
        }
    }

    /*@PostMapping
    public ResponseEntity<VaccineDTO> addVaccine(@Valid @RequestBody final VaccineCommand vaccineCommand) {
        return vaccineServiceImplementation.saveVaccine(vaccineCommand)
                .map(
                        vaccineDTO -> ResponseEntity
                            .status(HttpStatus.CREATED)
                            .body(vaccineDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                            .status(HttpStatus.CONFLICT)
                            .build()
                );
    }*/


    /*@PutMapping("/{researchName}")
    public ResponseEntity<VaccineDTO> updateVaccine(@PathVariable final String researchName, @Valid @RequestBody final VaccineCommand vaccineCommad) {
        return vaccineServiceImplementation.updateVaccine(researchName, vaccineCommad)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }*/

    /*@ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{researchName}")
    public void delete(@PathVariable final String researchName){
        System.out.println(researchName);
        vaccineServiceImplementation.deleteVaccine(researchName);
    }*/


}
