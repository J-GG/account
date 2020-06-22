package fr.jg.account.ports.secondary.user;

import fr.jg.account.domain.user.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User create(User user);

    User update(User user);

    List<User> getAll();

    User get(UUID userId);

    void delete(UUID userId);
}
