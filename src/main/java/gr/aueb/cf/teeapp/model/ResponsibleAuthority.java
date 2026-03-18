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
    private String name;

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "responsibleAuthority")
    private Set<Subproject> subprojects = new HashSet<>();

    public Set<Subproject> getAllSubprojects() {
        if (subprojects == null) subprojects = new HashSet<>();
        return Collections.unmodifiableSet(subprojects);
    }

    public void addSubproject(Subproject subproject) {
        if (subprojects == null) subprojects = new HashSet<>();
        subprojects.add(subproject);
        subproject.setResponsibleAuthority(this);
    }

    public void removeSubproject(Subproject subproject) {
        if (subprojects == null) return;
        subprojects.remove(subproject);
        subproject.setResponsibleAuthority(null);
    }
}
