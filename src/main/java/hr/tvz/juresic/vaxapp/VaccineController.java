package hr.tvz.juresic.vaxapp;

import org.springframework.web.bind.annotation.*;

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
        System.out.println("Pristup nasem API-u! Dohvat svih cijepiva. :D");
        return vaccineServiceImplementation.findAll();
    }

    @GetMapping(params = "researchName")
    public VaccineDTO getVaccineByResearchName(@RequestParam final String researchName) {
        System.out.println("Dohvat cjepiva s imenom = " + researchName);
        return vaccineServiceImplementation.findVaccineByResearchName(researchName);
    }

    @GetMapping(params = "oneDose")
    public List<VaccineDTO> getVaccineByDose(@RequestParam final boolean oneDose) {
        if (oneDose == true) {
            System.out.println("Vracam cjepiva s jednom dozom");
            return vaccineServiceImplementation.findVaccineWithOneDose();
        } else {
            System.out.println("Vracam cjepiva s vise doza");
            return vaccineServiceImplementation.findVaccineWithMultipleDoses();
        }
    }

    //REST API TEST
    /*@GetMapping
    public String getAllVaccines(){
        return "All vaccines";
    }

    @GetMapping(params = "researchName")
    public String getVaccineByResearchName(@RequestParam final String researchName) {
        return researchName;
    }*/

}
