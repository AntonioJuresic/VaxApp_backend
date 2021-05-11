package hr.tvz.juresic.vaxapp.sideeffect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SideEffectDTO {

    String shortDescription;
    String description;
    Integer frequency;

    @Override
    public String toString() {
        return "SideEffectDTO{"
                + "shortDescription" + this.shortDescription + "'\n,"
                + "description" + this.description + "'\n,"
                + "frequency" + this.frequency + "'}";
    }

}
