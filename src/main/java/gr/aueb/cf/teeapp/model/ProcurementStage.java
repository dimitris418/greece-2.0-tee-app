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
@Table(name = "procurement_stages")
public class ProcurementStage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "procurementStage")
    private Set<SubProjectProcurementStage> subProjectProcurementStages = new HashSet<>();


    public Set<SubProjectProcurementStage> getAllSubprojectsProcurementStages() {
        if (subProjectProcurementStages == null) subProjectProcurementStages = new HashSet<>();
        return Collections.unmodifiableSet(subProjectProcurementStages);
    }

        public void addSubprojectProcurementStage(SubProjectProcurementStage subProjectProcurementStage ) {
            if (subProjectProcurementStages == null) subProjectProcurementStages = new HashSet<>();
            subProjectProcurementStages.add(subProjectProcurementStage);
            subProjectProcurementStage.setProcurementStage(this);
        }

        public void removeSubprojectProcurementStage(SubProjectProcurementStage subProjectProcurementStage) {
            if (subProjectProcurementStages == null) return;
            subProjectProcurementStages.remove(subProjectProcurementStage);
            subProjectProcurementStage.setProcurementStage(null);
        }
    }
