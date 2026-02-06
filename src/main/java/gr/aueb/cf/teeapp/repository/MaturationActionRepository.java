package gr.aueb.cf.teeapp.repository;

import gr.aueb.cf.teeapp.model.MaturationAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface MaturationActionRepository extends JpaRepository<MaturationAction, Long>,
        JpaSpecificationExecutor<MaturationAction> {

    Optional<MaturationAction> findById(String id);
    Optional<MaturationAction> findByUuid(String uuid);
    Optional<MaturationAction> findByReferenceNumber(String referenceNumber);
    List<MaturationAction> findByStartDate(String startDate);
    List<MaturationAction> findByEndDate(String endDate);
    List<MaturationAction> findByDescription(String description);
}
