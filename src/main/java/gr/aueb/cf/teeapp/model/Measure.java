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
@Table(name = "measures")
public class Measure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "component_id")
    private Component component;

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "measure")
    private Set<Project> projects = new HashSet<>();

    public Set<Project> getAllProjects() {
        if (projects == null) projects = new HashSet<>();
        return Collections.unmodifiableSet(projects);
    }

    public void addProject(Project project) {
        if (projects == null) projects = new HashSet<>();
        projects.add(project);
        project.setMeasure(this);
    }

    public void removeProject(Project project) {
        projects.remove(project);
        project.setMeasure(null);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultant_id")
    private Consultant consultant;
}
