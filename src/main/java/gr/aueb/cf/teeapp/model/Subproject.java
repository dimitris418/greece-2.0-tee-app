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
@Table(name = "subprojects")
public class Subproject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "subproject")
    private Set<SubProjectProcurementStage> subProjectProcurementStages = new HashSet<>();

    public Set<SubProjectProcurementStage> getAllSubprojectsProcurementStages() {
        if (subProjectProcurementStages == null) subProjectProcurementStages = new HashSet<>();
        return Collections.unmodifiableSet(subProjectProcurementStages);
    }

    public void addSubprojectProcurementStage(SubProjectProcurementStage subProjectProcurementStage ) {
        if (subProjectProcurementStages == null) subProjectProcurementStages = new HashSet<>();
        subProjectProcurementStages.add(subProjectProcurementStage);
        subProjectProcurementStage.setSubproject(this);
    }

    public void removeSubprojectProcurementStage(SubProjectProcurementStage subProjectProcurementStage) {
        if (subProjectProcurementStages == null) return;
        subProjectProcurementStages.remove(subProjectProcurementStage);
        subProjectProcurementStage.setSubproject(null);
    }
}
