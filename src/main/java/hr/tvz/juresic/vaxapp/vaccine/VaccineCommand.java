package hr.tvz.juresic.vaxapp.vaccine;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

@Data
public class VaccineCommand {

    @NotBlank(message = "Research name must not be empty")
    private String researchName;

    @NotBlank(message = "Manufacturer name must not be empty")
    private String manufacturerName;

    @NotBlank(message = "Vaccine type must not be empty")
    @Pattern(regexp="^(RNA|VIRAL_VECTOR)$", message="Vaccine type must be RNA or VIRAL_VECTOR")
    private String type;

    @NotNull(message = "Number of doses must not be empty")
    @PositiveOrZero(message = "Number of doses must be zero or more")
    private Integer numberOfShots;

    @NotNull(message = "Available doses must not be empty")
    @PositiveOrZero(message = "Available doses must be zero or more")
    private Integer availableDoses;
}
