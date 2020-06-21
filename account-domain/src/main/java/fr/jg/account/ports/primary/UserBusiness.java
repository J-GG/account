package fr.jg.account.ports.primary;

import fr.jg.account.domain.User;

import java.util.List;
import java.util.UUID;

public interface UserBusiness {
    User create(User user);

    List<User> getAll();

    User get(UUID userId);

    void delete(UUID userId);
}
