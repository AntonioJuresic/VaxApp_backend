package hr.tvz.juresic.vaxapp;

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

    @Column(name="researchName")
    private String researchName;

    @Column(name="manufacturerName")
    private String manufacturerName;

    @Column(name="vaccineType")
    @Enumerated(EnumType.STRING)
    @JoinTable(name = "VaccineType", joinColumns = @JoinColumn(name = "vaccineTypeName"))
    private VaccineType vaccineType;

    @Column(name="numberOfDoses")
    private Integer numberOfDoses;

    @Column(name="availableDoses")
    private Integer availableDoses;

    @OneToMany(mappedBy = "vaccine", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<SideEffect> sideEffectList;
}
