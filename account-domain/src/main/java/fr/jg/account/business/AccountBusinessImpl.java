package fr.jg.account.business;

import fr.jg.account.domain.Account;
import fr.jg.account.ports.primary.AccountBusiness;
import fr.jg.account.ports.secondary.AccountService;

import java.util.List;
import java.util.UUID;

public class AccountBusinessImpl implements AccountBusiness {

    private final AccountService accountService;

    public AccountBusinessImpl(final AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public Account create(final Account account) {
        return this.accountService.create(account);
    }

    @Override
    public List<Account> getAll() {
        return this.accountService.getAll();
    }

    @Override
    public Account get(final UUID accountId) {
        return this.accountService.get(accountId);
    }
}
