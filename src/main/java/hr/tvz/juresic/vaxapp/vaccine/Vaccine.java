package hr.tvz.juresic.vaxapp.vaccine;

import hr.tvz.juresic.vaxapp.sideeffect.SideEffect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

enum VaccineType {
    RNA, VIRAL_VECTOR
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="Vaccine")
public class Vaccine {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="research_name")
    private String researchName;

    @Column(name="manufacturer_name")
    private String manufacturerName;

    @Column(name="vaccine_type")
    @Enumerated(EnumType.STRING)
    @JoinTable(name = "vaccine_type", joinColumns = @JoinColumn(name = "vaccine_type_name"))
    private VaccineType vaccineType;

    @Column(name="number_of_shots")
    private Integer numberOfShots;

    @Column(name="available_doses")
    private Integer availableDoses;

    @OneToMany(mappedBy = "vaccine", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<SideEffect> sideEffectList;
}
