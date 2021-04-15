package hr.tvz.juresic.vaxapp;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class SideEffectCommand {
    @NotBlank(message = "Short description must not be empty")
    private String shortDescription;

    @NotNull(message = "Fvailable must not be empty")
    @PositiveOrZero(message = "Frequency must be zero or more")
    private Integer frequency;

    @NotBlank(message = "Long description must not be empty")
    private String longDescription;

}
