package fr.jg.account.business;

import fr.jg.account.domain.Account;
import fr.jg.account.domain.User;
import fr.jg.account.ports.primary.UserBusiness;
import fr.jg.account.ports.secondary.UserService;

import java.util.List;
import java.util.UUID;

public class UserBusinessImpl implements UserBusiness {

    private final UserService userService;

    public UserBusinessImpl(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public User create(final User user) {
        return userService.create(user);
    }

    @Override
    public List<User> getAll() {
        return this.userService.getAll();
    }

    @Override
    public User addAccountToUser(final UUID userId, final Account account) {
        final User user = this.userService.get(userId);
        user.addAccount(account);
        return this.userService.update(user);
    }

    @Override
    public User get(final UUID userId) {
        return this.userService.get(userId);
    }

    @Override
    public void delete(final UUID userId) {
        this.userService.delete(userId);
    }
}
