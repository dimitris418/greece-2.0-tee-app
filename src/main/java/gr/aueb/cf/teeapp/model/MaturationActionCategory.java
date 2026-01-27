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
@Table(name = "maturation_actions_categories")
public class MaturationActionCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "maturation_action_category")
    private Set<MaturationAction> maturationActions = new HashSet<>();

    public Set<MaturationAction> getAllMaturationActions() {
        if (maturationActions == null) maturationActions = new HashSet<>();
        return Collections.unmodifiableSet(maturationActions);
    }

    public void addMaturationAction(MaturationAction maturationAction) {
        if (maturationActions == null) maturationActions = new HashSet<>();
        maturationActions.add(maturationAction);
        maturationAction.setMaturationActionCategory(this);
    }

    public void removeMaturationAction(MaturationAction maturationAction) {
        maturationActions.remove(maturationAction);
        maturationAction.setMaturationActionCategory(null);
    }
}
