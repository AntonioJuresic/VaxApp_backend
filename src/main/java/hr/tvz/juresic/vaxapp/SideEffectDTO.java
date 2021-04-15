package hr.tvz.juresic.vaxapp;

import lombok.Data;

@Data
public class SideEffectDTO {
    private String shortDescription;
    private Integer frequency;
    private String longDescription;


    public SideEffectDTO(String shortDescription, Integer frequency, String longDescription) {
        this.shortDescription = shortDescription;
        this.frequency = frequency;
        this.longDescription = longDescription;
    }

    @Override
    public String toString(){
        return "SideEffectDTO{"
                + "shortDescription='" + this.shortDescription + "'\n,"
                + "frequency='" + this.frequency + "'\n,"
                + "longDescription='" + this.longDescription + "'}";
    }
}
