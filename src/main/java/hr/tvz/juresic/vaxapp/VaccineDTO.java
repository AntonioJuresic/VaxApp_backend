package hr.tvz.juresic.vaxapp;

import lombok.Data;

@Data
public class VaccineDTO {
    private String manufacturerName;
    private Integer numberOfDossesNeeded;

    public VaccineDTO(String manufacturerName, Integer numberOfDossesNeeded) {
        this.manufacturerName = manufacturerName;
        this.numberOfDossesNeeded = numberOfDossesNeeded;
    }

    @Override
    public String toString(){
        return "VaccineDTO{"
                + "manufacturerName='" + this.manufacturerName + "'\n,"
                + "numberOfDossesNeeded='" + this.numberOfDossesNeeded + "'}";
    }
}
