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
@Table(name = "procurement_types")
public class ProcurementType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "procurementType")
    private Set<ProcurementStageCategory> procurementStagesCategories = new HashSet<>();

    public Set<ProcurementStageCategory> getAllProcurementStagesCategories() {
        if (procurementStagesCategories == null) procurementStagesCategories = new HashSet<>();
        return Collections.unmodifiableSet(procurementStagesCategories);
    }

    public void addProcurementStageCategory(ProcurementStageCategory procurementStageCategory) {
        if (procurementStagesCategories == null) procurementStagesCategories = new HashSet<>();
        procurementStagesCategories.add(procurementStageCategory);
        procurementStageCategory.setProcurementType(this);
    }

    public void removeProcurementStageCategory(ProcurementStageCategory procurementStageCategory) {
        if (procurementStagesCategories == null) return;
        procurementStagesCategories.remove(procurementStageCategory);
        procurementStageCategory.setProcurementType(null);
    }
}
