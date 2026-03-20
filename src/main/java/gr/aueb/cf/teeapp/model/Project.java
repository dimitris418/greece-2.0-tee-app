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
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measure_id")
    private Measure measure;

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "project")
    private Set<Portfolio> portfolios = new HashSet<>();

    public Set<Portfolio> getAllPortfolios() {
        if (portfolios == null) portfolios = new HashSet<>();
        return Collections.unmodifiableSet(portfolios);
    }

    public void addPortfolio(Portfolio portfolio) {
        if (portfolios == null) portfolios = new HashSet<>();
        portfolios.add(portfolio);
        portfolio.setProject(this);
    }

    public void removePortfolio(Portfolio portfolio) {
        if (portfolios == null) return;
        portfolios.remove(portfolio);
        portfolio.setProject(null);
    }
}
