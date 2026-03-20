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
@Table(name = "pillars")
public class Pillar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "pillar")
    private Set<Component> components = new HashSet<>();

    public Set<Component> getAllComponents() {
        if (components == null) components = new HashSet<>();
        return Collections.unmodifiableSet(components);
    }

    public void addComponent(Component component) {
        if (components == null) components = new HashSet<>();
        components.add(component);
        component.setPillar(this);
    }

    public void removeComponent(Component component) {
        if (components == null) return;
        components.remove(component);
        component.setPillar(null);
    }
}
