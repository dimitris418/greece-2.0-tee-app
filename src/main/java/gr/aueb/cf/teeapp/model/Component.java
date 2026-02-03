package gr.aueb.cf.teeapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "components")
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pillar_id")
    private Pillar pillar;

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "component")
    private Set<Measure> measures = new HashSet<>();

    public Set<Measure> getAllMeasures() {
        if (measures == null) measures = new HashSet<>();
        return Collections.unmodifiableSet(measures);
    }

    public void addMeasure(Measure measure) {
        if (measures == null) measures = new HashSet<>();
        measures.add(measure);
        measure.setComponent(this);
    }

    public void removeMeasure(Measure measure) {
        measures.remove(measure);
        measure.setComponent(null);
    }
}
