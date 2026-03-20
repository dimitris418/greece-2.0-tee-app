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
@Table(name = "portfolios")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "portfolio")
    private Set<Subproject> subprojects = new HashSet<>();

    public Set<Subproject> getAllSubprojects() {
        if (subprojects == null) subprojects = new HashSet<>();
        return Collections.unmodifiableSet(subprojects);
    }

    public void addSubproject(Subproject subproject) {
        if (subprojects == null) subprojects = new HashSet<>();
        subprojects.add(subproject);
        subproject.setPortfolio(this);
    }

    public void removeSubproject(Subproject subproject) {
        if (subprojects == null) return;
        subprojects.remove(subproject);
        subproject.setPortfolio(null);
    }
}
