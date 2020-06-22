package fr.jg.account.services.estate.tradingAccount;

import fr.jg.account.domain.estate.tradingAccount.TradingTransaction;
import fr.jg.account.mappers.CycleAvoidingMappingContext;
import fr.jg.account.mappers.estate.tradingAccount.TradingTransactionMapper;
import fr.jg.account.ports.secondary.estate.tradingAccount.TradingTransactionService;
import fr.jg.account.repositories.estate.tradingAccount.TradingTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TradingTransactionServiceImpl implements TradingTransactionService {

    @Autowired
    private TradingTransactionRepository tradingTransactionRepository;

    @Autowired
    private TradingTransactionMapper tradingTransactionMapper;

    @Autowired
    private CycleAvoidingMappingContext mappingContext;

    @Override
    public TradingTransaction create(final TradingTransaction tradingTransaction) {
        return this.tradingTransactionMapper.modelToDomain(this.tradingTransactionRepository.save(this.tradingTransactionMapper.domainToModel(tradingTransaction, this.mappingContext)), this.mappingContext);
    }

    @Override
    public TradingTransaction update(final TradingTransaction tradingTransaction) {
        return this.tradingTransactionMapper.modelToDomain(this.tradingTransactionRepository.save(this.tradingTransactionMapper.domainToModel(tradingTransaction, this.mappingContext)), this.mappingContext);
    }

    @Override
    public List<TradingTransaction> getAll() {
        return this.tradingTransactionRepository.findAll().stream().map(account -> this.tradingTransactionMapper.modelToDomain(account, this.mappingContext)).collect(Collectors.toList());
    }

    @Override
    public TradingTransaction get(final UUID tradingTransactionId) {
        return this.tradingTransactionRepository.findById(tradingTransactionId).map(account -> this.tradingTransactionMapper.modelToDomain(account, this.mappingContext)).orElseThrow();
    }

    @Override
    public List<TradingTransaction> getByTradingAccountId(final UUID tradingAccountId) {
        return this.tradingTransactionMapper.modelToDomain(this.tradingTransactionRepository.findBytradingAccountId(tradingAccountId), this.mappingContext);
    }

    @Override
    public void delete(final UUID tradingTransactionId) {
        this.tradingTransactionRepository.deleteById(tradingTransactionId);
    }
}
