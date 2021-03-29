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
    public ResponseEntity<VaccineDTO> addVaccine(@Valid @RequestBody final VaccineCommand vaccineCommand) {
        VaccineDTO newVaccineDTO = vaccineServiceImplementation.saveVaccine(vaccineCommand);

        if(newVaccineDTO != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(newVaccineDTO);
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PutMapping("/{researchName}")
    public ResponseEntity<VaccineDTO> updateVaccine(@PathVariable final String researchName, @Valid @RequestBody final VaccineCommand vaccineCommand) {
        VaccineDTO updatedVaccineDTO = vaccineServiceImplementation.updateVaccine(researchName, vaccineCommand);

        if(updatedVaccineDTO != null)
            return ResponseEntity.status(HttpStatus.OK).body(updatedVaccineDTO);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /*@ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{researchName}")
    public void delete(@PathVariable final String researchName){
        System.out.println(researchName);
        vaccineServiceImplementation.deleteVaccine(researchName);
    }*/


}
