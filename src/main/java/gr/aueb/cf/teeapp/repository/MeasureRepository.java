package gr.aueb.cf.teeapp.repository;

import gr.aueb.cf.teeapp.model.Measure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasureRepository extends JpaRepository<Measure, Long> {
}
