package gr.aueb.cf.teeapp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "consultants")
public class Consultant extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uuid;

    @ColumnDefault("true")
    private Boolean isActive;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "personal_info_id")
    private PersonalInfo personalInfo;

    @PrePersist
    public void initializeUUID() {
        if (uuid == null) uuid = UUID.randomUUID().toString();
    }

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "consultant")
    private Set<Measure> measures = new HashSet<>();

    public Set<Measure> getAllMeasures() {
        if (measures == null) measures = new HashSet<>();
        return Collections.unmodifiableSet(measures);
    }

    public void addMeasure(Measure measure) {
        if (measures == null) measures = new HashSet<>();
        measures.add(measure);
        measure.setConsultant(this);
    }

    public void removeMeasure(Measure measure) {
        measures.remove(measure);
        measure.setConsultant(null);
    }
}
