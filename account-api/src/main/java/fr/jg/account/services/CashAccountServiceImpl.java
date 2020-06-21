package fr.jg.account.services;

import fr.jg.account.domain.CashAccount;
import fr.jg.account.mappers.CashAccountMapper;
import fr.jg.account.mappers.CycleAvoidingMappingContext;
import fr.jg.account.ports.secondary.CashAccountService;
import fr.jg.account.repositories.CashAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CashAccountServiceImpl implements CashAccountService {

    @Autowired
    private CashAccountRepository cashAccountRepository;

    @Autowired
    private CashAccountMapper cashAccountMapper;

    @Autowired
    private CycleAvoidingMappingContext mappingContext;

    @Override
    public CashAccount create(final CashAccount cashAccount) {
        return this.cashAccountMapper.modelToDomain(this.cashAccountRepository.save(this.cashAccountMapper.domainToModel(cashAccount, this.mappingContext)), this.mappingContext);
    }

    @Override
    public CashAccount update(final CashAccount cashAccount) {
        return this.cashAccountMapper.modelToDomain(this.cashAccountRepository.save(this.cashAccountMapper.domainToModel(cashAccount, this.mappingContext)), this.mappingContext);
    }

    @Override
    public List<CashAccount> getAll() {
        return this.cashAccountRepository.findAll().stream().map(account -> this.cashAccountMapper.modelToDomain(account, this.mappingContext)).collect(Collectors.toList());
    }

    @Override
    public CashAccount get(final UUID accountId) {
        return this.cashAccountRepository.findById(accountId).map(account -> this.cashAccountMapper.modelToDomain(account, this.mappingContext)).orElseThrow();
    }

    @Override
    public void delete(final UUID accountId) {
        this.cashAccountRepository.deleteById(accountId);
    }
}
