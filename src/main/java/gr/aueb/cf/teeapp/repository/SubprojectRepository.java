package gr.aueb.cf.teeapp.repository;

import gr.aueb.cf.teeapp.model.Subproject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SubprojectRepository extends JpaRepository<Subproject, Long>,
        JpaSpecificationExecutor<Subproject> {
}

