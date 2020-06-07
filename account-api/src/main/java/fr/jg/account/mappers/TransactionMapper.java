package fr.jg.account.mappers;

import fr.jg.account.dao.TransactionDao;
import fr.jg.account.domain.Transaction;
import fr.jg.account.models.TransactionModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    Transaction modelToDomain(TransactionModel transactionModel);

    List<Transaction> modelToDomain(List<TransactionModel> transactionModels);

    TransactionDao domainToDao(Transaction transaction);

    List<TransactionDao> domainToDao(List<Transaction> transactions);

    Transaction daoToDomain(TransactionDao transactionDao);

    List<Transaction> daoToDomain(List<TransactionDao> accountDaos);

    TransactionModel domainToModel(Transaction transaction);

    List<TransactionModel> domainToModel(List<Transaction> transactions);
}
