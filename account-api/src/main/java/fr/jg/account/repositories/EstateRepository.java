package fr.jg.account.repositories;

import fr.jg.account.models.EstateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EstateRepository extends JpaRepository<EstateModel, UUID> {

    EstateModel findByUserId(final UUID userId);

}
