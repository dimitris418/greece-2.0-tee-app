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
@Table(name = "responsible_authorities")
public class ResponsibleAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "responsibleAuthority")
    private Set<Project> projects = new HashSet<>();

    public Set<Project> getAllProjects() {
        if (projects == null) projects = new HashSet<>();
        return Collections.unmodifiableSet(projects);
    }

    public void addProject(Project project) {
        if (projects == null) projects = new HashSet<>();
        projects.add(project);
        project.setResponsibleAuthority(this);
    }

    public void removeProject(Project project) {
        if (projects == null) return;
        projects.remove(project);
        project.setResponsibleAuthority(null);
    }
}
