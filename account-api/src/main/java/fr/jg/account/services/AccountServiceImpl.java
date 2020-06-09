package fr.jg.account.services;

import fr.jg.account.domain.Account;
import fr.jg.account.mappers.AccountMapper;
import fr.jg.account.mappers.CycleAvoidingMappingContext;
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

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    CycleAvoidingMappingContext mappingContext;

    @Override
    public Account create(final Account account) {
        return this.accountMapper.modelToDomain(this.accountRepository.save(this.accountMapper.domainToModel(account, this.mappingContext)), this.mappingContext);
    }

    @Override
    public Account update(final Account account) {
        return this.accountMapper.modelToDomain(this.accountRepository.save(this.accountMapper.domainToModel(account, this.mappingContext)), this.mappingContext);
    }

    @Override
    public List<Account> getAll() {
        return this.accountRepository.findAll().stream().map(account -> this.accountMapper.modelToDomain(account, this.mappingContext)).collect(Collectors.toList());
    }

    @Override
    public Account get(final UUID accountId) {
        return this.accountRepository.findById(accountId).map(account -> this.accountMapper.modelToDomain(account, this.mappingContext)).orElseThrow();
    }

    @Override
    public void delete(final UUID accountId) {
        this.accountRepository.deleteById(accountId);
    }
}
