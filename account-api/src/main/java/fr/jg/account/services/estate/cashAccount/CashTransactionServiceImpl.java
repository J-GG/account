package fr.jg.account.services.estate.cashAccount;

import fr.jg.account.domain.estate.cashAccount.CashTransaction;
import fr.jg.account.mappers.CycleAvoidingMappingContext;
import fr.jg.account.mappers.estate.cashAccount.CashTransactionMapper;
import fr.jg.account.ports.secondary.estate.cashAccount.CashTransactionService;
import fr.jg.account.repositories.estate.cashAccount.CashTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CashTransactionServiceImpl implements CashTransactionService {

    @Autowired
    private CashTransactionRepository cashTransactionRepository;

    @Autowired
    private CashTransactionMapper cashTransactionMapper;

    @Autowired
    private CycleAvoidingMappingContext mappingContext;

    @Override
    public CashTransaction create(final CashTransaction cashTransaction) {
        return this.cashTransactionMapper.modelToDomain(this.cashTransactionRepository.save(this.cashTransactionMapper.domainToModel(cashTransaction, this.mappingContext)), this.mappingContext);
    }

    @Override
    public List<CashTransaction> getAll() {
        return this.cashTransactionMapper.modelToDomain(this.cashTransactionRepository.findAll(), this.mappingContext);
    }

    @Override
    public List<CashTransaction> getByCashAccountId(final UUID cashAccountId) {
        return this.cashTransactionMapper.modelToDomain(this.cashTransactionRepository.findByCashAccountId(cashAccountId), this.mappingContext);
    }

    @Override
    public CashTransaction get(final UUID transactionId) {
        return this.cashTransactionRepository.findById(transactionId).map(transaction -> this.cashTransactionMapper.modelToDomain(transaction, this.mappingContext)).orElseThrow();
    }

    @Override
    public void delete(final UUID transactionId) {
        this.cashTransactionRepository.deleteById(transactionId);
    }
}
