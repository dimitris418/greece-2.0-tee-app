package gr.aueb.cf.teeapp.model;

import gr.aueb.cf.teeapp.core.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "subprojects_procurement_stages")
public class SubProjectProcurementStage extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uuid;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(unique = true)
    private String referenceNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "procurement_stage_id")
    private ProcurementStage procurementStage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subproject_id")
    private Subproject subproject;

    @PrePersist
    public void initializeUUID() {
        if (uuid == null) uuid = UUID.randomUUID().toString();
    }
}
