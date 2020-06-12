package fr.jg.account.services;

import fr.jg.account.domain.Transaction;
import fr.jg.account.mappers.CycleAvoidingMappingContext;
import fr.jg.account.mappers.TransactionMapper;
import fr.jg.account.ports.secondary.TransactionService;
import fr.jg.account.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private CycleAvoidingMappingContext mappingContext;

    @Override
    public Transaction create(final Transaction transaction) {
        return this.transactionMapper.modelToDomain(this.transactionRepository.save(this.transactionMapper.domainToModel(transaction, this.mappingContext)), this.mappingContext);
    }

    @Override
    public List<Transaction> getAll() {
        return this.transactionMapper.modelToDomain(this.transactionRepository.findAll(), this.mappingContext);
    }

    @Override
    public List<Transaction> getByAccountId(final UUID accountId) {
        return this.transactionMapper.modelToDomain(this.transactionRepository.findByAccountId(accountId), this.mappingContext);
    }

    @Override
    public Transaction get(final UUID transactionId) {
        return this.transactionRepository.findById(transactionId).map(transaction -> this.transactionMapper.modelToDomain(transaction, this.mappingContext)).orElseThrow();
    }

    @Override
    public void delete(final UUID transactionId) {
        this.transactionRepository.deleteById(transactionId);
    }
}
