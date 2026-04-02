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
@Table(name = "procurement_stages_categories")
public class ProcurementStageCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "procurement_type_id")
    private ProcurementType procurementType;

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "procurementStageCategory")
    private Set<ProcurementStage> procurementStages = new HashSet<>();

    public Set<ProcurementStage> getAllProcurementStages() {
        if (procurementStages == null) procurementStages = new HashSet<>();
        return Collections.unmodifiableSet(procurementStages);
    }

    public void addProcurementStage(ProcurementStage procurementStage) {
        if (procurementStages == null) procurementStages = new HashSet<>();
        procurementStages.add(procurementStage);
        procurementStage.setProcurementStageCategory(this);
    }

    public void removeProcurementStage(ProcurementStage procurementStage) {
        if (procurementStages == null) return;
        procurementStages.remove(procurementStage);
        procurementStage.setProcurementStageCategory(null);
    }
}
