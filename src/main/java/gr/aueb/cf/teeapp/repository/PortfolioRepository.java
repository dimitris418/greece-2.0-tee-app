package gr.aueb.cf.teeapp.repository;

import gr.aueb.cf.teeapp.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
}
