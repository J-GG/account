package fr.jg.account.services;

import fr.jg.account.domain.TradingAccount;
import fr.jg.account.mappers.CycleAvoidingMappingContext;
import fr.jg.account.mappers.TradingAccountMapper;
import fr.jg.account.ports.secondary.TradingAccountService;
import fr.jg.account.repositories.TradingAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TradingAccountServiceImpl implements TradingAccountService {

    @Autowired
    private TradingAccountRepository tradingAccountRepository;

    @Autowired
    private TradingAccountMapper tradingAccountMapper;

    @Autowired
    private CycleAvoidingMappingContext mappingContext;

    @Override
    public TradingAccount create(final TradingAccount tradingAccount) {
        return this.tradingAccountMapper.modelToDomain(this.tradingAccountRepository.save(this.tradingAccountMapper.domainToModel(tradingAccount, this.mappingContext)), this.mappingContext);
    }

    @Override
    public TradingAccount update(final TradingAccount tradingAccount) {
        return this.tradingAccountMapper.modelToDomain(this.tradingAccountRepository.save(this.tradingAccountMapper.domainToModel(tradingAccount, this.mappingContext)), this.mappingContext);
    }

    @Override
    public List<TradingAccount> getAll() {
        return this.tradingAccountRepository.findAll().stream().map(account -> this.tradingAccountMapper.modelToDomain(account, this.mappingContext)).collect(Collectors.toList());
    }

    @Override
    public TradingAccount get(final UUID tradingAccountId) {
        return this.tradingAccountRepository.findById(tradingAccountId).map(account -> this.tradingAccountMapper.modelToDomain(account, this.mappingContext)).orElseThrow();
    }

    @Override
    public void delete(final UUID tradingAccountId) {
        this.tradingAccountRepository.deleteById(tradingAccountId);
    }
}
