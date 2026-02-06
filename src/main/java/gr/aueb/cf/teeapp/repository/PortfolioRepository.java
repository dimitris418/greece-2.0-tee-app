package gr.aueb.cf.teeapp.repository;

import gr.aueb.cf.teeapp.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long>,
        JpaSpecificationExecutor<Portfolio> {
}

