package gr.aueb.cf.teeapp.repository;

import gr.aueb.cf.teeapp.model.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ComponentRepository extends JpaRepository<Component, Long>,
        JpaSpecificationExecutor<Component> {
}


