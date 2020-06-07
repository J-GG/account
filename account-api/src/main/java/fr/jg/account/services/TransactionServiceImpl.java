package fr.jg.account.services;

import fr.jg.account.domain.Transaction;
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
    TransactionRepository transactionRepository;

    @Override
    public Transaction create(final Transaction transaction) {
        return null;
    }

    @Override
    public List<Transaction> getAll() {
        return TransactionMapper.INSTANCE.modelToDomain(this.transactionRepository.findAll());
    }

    @Override
    public List<Transaction> getAllByAccountId(final UUID accountId) {
        return TransactionMapper.INSTANCE.modelToDomain(this.transactionRepository.findByAccountId(accountId));
    }

    @Override
    public Transaction get(final UUID transactionId) {
        return this.transactionRepository.findById(transactionId).map(TransactionMapper.INSTANCE::modelToDomain).orElseThrow();
    }
}
