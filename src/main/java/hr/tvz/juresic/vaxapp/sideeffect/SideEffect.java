package hr.tvz.juresic.vaxapp.sideeffect;

import hr.tvz.juresic.vaxapp.vaccine.Vaccine;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="side_effect")
public class SideEffect implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue
    private Long id;

    @Column(name="short_description")
    String shortDescription;

    @Column(name="description")
    String description;

    @Column(name="frequency")
    Integer frequency;

    @ManyToOne
    @JoinColumn(name="vaccine_id")
    Vaccine vaccine;
}
