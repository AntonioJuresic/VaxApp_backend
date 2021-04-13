package hr.tvz.juresic.vaxapp;

import lombok.Data;

@Data
public class VaccineDTO {
    private String researchName;
    private String manufacturerName;
    private Integer numberOfDossesNeeded;

    public VaccineDTO(String researchName, String manufacturerName, Integer numberOfDossesNeeded) {
        this.researchName = researchName;
        this.manufacturerName = manufacturerName;
        this.numberOfDossesNeeded = numberOfDossesNeeded;
    }

    @Override
    public String toString(){
        return "VaccineDTO{"
                + "researchName='" + this.researchName + "'\n,"
                + "manufacturerName='" + this.manufacturerName + "'\n,"
                + "numberOfDossesNeeded='" + this.numberOfDossesNeeded + "'}";
    }
}
