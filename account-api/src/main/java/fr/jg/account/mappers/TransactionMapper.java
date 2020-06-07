package fr.jg.account.mappers;

import fr.jg.account.domain.Transaction;
import fr.jg.account.dto.TransactionDto;
import fr.jg.account.models.TransactionModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    Transaction modelToDomain(TransactionModel transactionModel);

    List<Transaction> modelToDomain(List<TransactionModel> transactionModels);

    TransactionDto domainToDao(Transaction transaction);

    List<TransactionDto> domainToDao(List<Transaction> transactions);

    Transaction daoToDomain(TransactionDto transactionDto);

    List<Transaction> daoToDomain(List<TransactionDto> accountDaos);

    TransactionModel domainToModel(Transaction transaction);

    List<TransactionModel> domainToModel(List<Transaction> transactions);
}
