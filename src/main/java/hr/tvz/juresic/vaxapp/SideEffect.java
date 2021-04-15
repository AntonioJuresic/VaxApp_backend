package hr.tvz.juresic.vaxapp;

import lombok.Data;

@Data
public class SideEffect {
    private String shortDescription;
    private Integer frequency;
    private String longDescription;

    private SideEffect() {}

    public SideEffect(String shortDescription, Integer frequency, String longDescription) {
        this.shortDescription = shortDescription;
        this.frequency = frequency;
        this.longDescription = longDescription;
    }
}
