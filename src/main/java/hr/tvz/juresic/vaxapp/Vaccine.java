package hr.tvz.juresic.vaxapp;

import lombok.Data;

@Data
public class Vaccine {
    private String researchName;
    private String manufacturerName;

    private VaccineType vaccineType;
    public static enum VaccineType {
        RNA, VIRAL_VECTOR
    }

    private Integer numberOfDoses;
    private Integer availableDoses;

    public Vaccine() {}

    public Vaccine(String researchName, String manufacturerName, String vaccineType, Integer numberOfDoses, Integer availableDoses) {
        this.researchName = researchName;
        this.manufacturerName = manufacturerName;
        this.vaccineType = Vaccine.VaccineType.valueOf(vaccineType);
        this.numberOfDoses = numberOfDoses;
        this.availableDoses = availableDoses;
    }
}
