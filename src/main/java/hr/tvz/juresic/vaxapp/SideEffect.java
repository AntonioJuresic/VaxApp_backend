package hr.tvz.juresic.vaxapp;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="SideEffect")
public class SideEffect implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue
    private Long id;

    @Column(name="shortDescription")
    String shortDescription;

    @Column(name="longDescription")
    String longDescription;

    @Column(name="frequency")
    Integer frequency;

    @ManyToOne
    @JoinColumn(name="vaccineId")
    Vaccine vaccine;
}
