package hr.tvz.juresic.vaxapp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SideEffectDTO {

    String shortDescription;
    String longDescription;
    Integer frequency;

    @Override
    public String toString() {
        return "SideEffectDTO{"
                + "shortDescription" + this.shortDescription + "'\n,"
                + "longDescription" + this.longDescription + "'\n,"
                + "frequency" + this.frequency + "'}";
    }

}
