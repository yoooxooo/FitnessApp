package app.it_academy.fitnessAppAudit.dao;

import app.it_academy.fitnessAppAudit.domain.Audit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuditRepository extends PagingAndSortingRepository<Audit, UUID> {

    <S extends Audit> S save(S entity);

    Optional<Audit> findById(UUID uuid);

    Page<Audit> findAll(Pageable pageable);
}
