package gr.aueb.cf.teeapp.repository;

import gr.aueb.cf.teeapp.model.Consultant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultantRepository extends JpaRepository<Consultant, Long>,
        JpaSpecificationExecutor<Consultant> {


    Optional<Consultant> findByUserId(Long id);

    Optional<Consultant> findByUuid(String uuid);

    List<Consultant> findByUserLastname(String lastname);
}
