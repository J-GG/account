package fr.jg.account.services;

import fr.jg.account.domain.Account;
import fr.jg.account.mappers.AccountMapper;
import fr.jg.account.ports.secondary.AccountService;
import fr.jg.account.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account create(final Account account) {
        return null;
    }

    @Override
    public List<Account> getAll() {
        return this.accountRepository.findAll().stream().map(AccountMapper.INSTANCE::modelToDomain).collect(Collectors.toList());
    }

    @Override
    public Account get(final UUID accountId) {
        return this.accountRepository.findById(accountId).map(AccountMapper.INSTANCE::modelToDomain).orElseThrow();
    }
}
