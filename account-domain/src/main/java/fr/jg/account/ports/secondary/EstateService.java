package fr.jg.account.ports.secondary;

import fr.jg.account.domain.Estate;

import java.util.List;
import java.util.UUID;

public interface EstateService {
    Estate create(Estate estate);

    Estate update(Estate estate);

    List<Estate> getAll();

    Estate get(UUID estateId);

    Estate getByUserId(UUID userId);

    void delete(UUID estateId);
}
