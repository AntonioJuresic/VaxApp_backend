package hr.tvz.juresic.vaxapp.vaccine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccineDTO {
    private String researchName;
    private String manufacturerName;

    private String type;

    private Integer numberOfShots;
    private Integer availableDoses;

    @Override
    public String toString(){
        return "VaccineDTO{"
                + "researchName='" + this.researchName + "'\n,"
                + "manufacturerName='" + this.manufacturerName + "'\n,"
                + "type='" + this.type + "'\n,"
                + "numberOfShots='" + this.numberOfShots + "'\n,"
                + "availableDoses='" + this.availableDoses + "'}";
    }
}
