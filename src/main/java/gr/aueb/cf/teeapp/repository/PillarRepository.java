package gr.aueb.cf.teeapp.repository;

import gr.aueb.cf.teeapp.model.Pillar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PillarRepository extends JpaRepository<Pillar, Long>,
        JpaSpecificationExecutor<Pillar> {
}

