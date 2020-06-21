package fr.jg.account.services;

import fr.jg.account.domain.CashTransaction;
import fr.jg.account.mappers.CashTransactionMapper;
import fr.jg.account.mappers.CycleAvoidingMappingContext;
import fr.jg.account.ports.secondary.CashTransactionService;
import fr.jg.account.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CashTransactionServiceImpl implements CashTransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CashTransactionMapper cashTransactionMapper;

    @Autowired
    private CycleAvoidingMappingContext mappingContext;

    @Override
    public CashTransaction create(final CashTransaction cashTransaction) {
        return this.cashTransactionMapper.modelToDomain(this.transactionRepository.save(this.cashTransactionMapper.domainToModel(cashTransaction, this.mappingContext)), this.mappingContext);
    }

    @Override
    public List<CashTransaction> getAll() {
        return this.cashTransactionMapper.modelToDomain(this.transactionRepository.findAll(), this.mappingContext);
    }

    @Override
    public List<CashTransaction> getByCashAccountId(final UUID cashAccountId) {
        return this.cashTransactionMapper.modelToDomain(this.transactionRepository.findByCashAccountId(cashAccountId), this.mappingContext);
    }

    @Override
    public CashTransaction get(final UUID transactionId) {
        return this.transactionRepository.findById(transactionId).map(transaction -> this.cashTransactionMapper.modelToDomain(transaction, this.mappingContext)).orElseThrow();
    }

    @Override
    public void delete(final UUID transactionId) {
        this.transactionRepository.deleteById(transactionId);
    }
}
