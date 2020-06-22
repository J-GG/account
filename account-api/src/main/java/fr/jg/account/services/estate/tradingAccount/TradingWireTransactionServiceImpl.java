package fr.jg.account.services.estate.tradingAccount;

import fr.jg.account.domain.estate.tradingAccount.TradingWireTransaction;
import fr.jg.account.mappers.CycleAvoidingMappingContext;
import fr.jg.account.mappers.estate.tradingAccount.TradingWireTransactionMapper;
import fr.jg.account.ports.secondary.estate.tradingAccount.TradingWireTransactionService;
import fr.jg.account.repositories.estate.tradingAccount.TradingWireTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TradingWireTransactionServiceImpl implements TradingWireTransactionService {

    @Autowired
    private TradingWireTransactionRepository tradingWireTransactionRepository;

    @Autowired
    private TradingWireTransactionMapper tradingWireTransactionMapper;

    @Autowired
    private CycleAvoidingMappingContext mappingContext;

    @Override
    public TradingWireTransaction create(final TradingWireTransaction tradingWireTransaction) {
        return this.tradingWireTransactionMapper.modelToDomain(this.tradingWireTransactionRepository.save(this.tradingWireTransactionMapper.domainToModel(tradingWireTransaction, this.mappingContext)), this.mappingContext);
    }

    @Override
    public TradingWireTransaction update(final TradingWireTransaction tradingWireTransaction) {
        return this.tradingWireTransactionMapper.modelToDomain(this.tradingWireTransactionRepository.save(this.tradingWireTransactionMapper.domainToModel(tradingWireTransaction, this.mappingContext)), this.mappingContext);
    }

    @Override
    public List<TradingWireTransaction> getAll() {
        return this.tradingWireTransactionRepository.findAll().stream().map(account -> this.tradingWireTransactionMapper.modelToDomain(account, this.mappingContext)).collect(Collectors.toList());
    }

    @Override
    public TradingWireTransaction get(final UUID tradingWireTransactionId) {
        return this.tradingWireTransactionRepository.findById(tradingWireTransactionId).map(account -> this.tradingWireTransactionMapper.modelToDomain(account, this.mappingContext)).orElseThrow();
    }

    @Override
    public void delete(final UUID tradingWireTransactionId) {
        this.tradingWireTransactionRepository.deleteById(tradingWireTransactionId);
    }
}
