package fr.jg.account.business;

import fr.jg.account.domain.Estate;
import fr.jg.account.domain.User;
import fr.jg.account.ports.primary.EstateBusiness;
import fr.jg.account.ports.primary.UserBusiness;
import fr.jg.account.ports.secondary.UserService;

import java.util.List;
import java.util.UUID;

public class UserBusinessImpl implements UserBusiness {

    private final UserService userService;

    private final EstateBusiness estateBusiness;

    public UserBusinessImpl(final UserService userService, final EstateBusiness estateBusiness) {
        this.userService = userService;
        this.estateBusiness = estateBusiness;
    }

    @Override
    public User create(final User user) {
        final User createdUser = this.userService.create(user);
        final Estate estate = new Estate();
        estate.setUser(createdUser);
        createdUser.setEstate(this.estateBusiness.create(estate));

        return createdUser;
    }

    @Override
    public List<User> getAll() {
        return this.userService.getAll();
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
