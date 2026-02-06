package gr.aueb.cf.teeapp.repository;

import gr.aueb.cf.teeapp.model.Measure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MeasureRepository extends JpaRepository<Measure, Long>,
        JpaSpecificationExecutor<Measure> {
}

