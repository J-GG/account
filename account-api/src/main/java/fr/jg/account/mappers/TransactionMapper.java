package fr.jg.account.mappers;

import fr.jg.account.domain.Transaction;
import fr.jg.account.dto.TransactionDto;
import fr.jg.account.models.TransactionModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring", uses = AccountMapper.class)
public abstract class TransactionMapper {

    public abstract Transaction modelToDomain(TransactionModel transactionModel, @Context CycleAvoidingMappingContext context);

    public abstract List<Transaction> modelToDomain(List<TransactionModel> transactionModels, @Context CycleAvoidingMappingContext context);

    public abstract TransactionDto domainToDto(Transaction transaction);

    public abstract List<TransactionDto> domainToDto(List<Transaction> transactions);

    public abstract Transaction dtoToDomain(TransactionDto transactionDto);

    public abstract List<Transaction> dtoToDomain(List<TransactionDto> accountDtos);

    public abstract TransactionModel domainToModel(Transaction transaction, @Context CycleAvoidingMappingContext context);

    public abstract List<TransactionModel> domainToModel(List<Transaction> transactions, @Context CycleAvoidingMappingContext context);

    @AfterMapping
    void bigDecimalToLongAmount(final Transaction transaction, @MappingTarget final TransactionModel transactionModel) {
        transactionModel.setAmount(transaction.getAmount().multiply(BigDecimal.valueOf(Math.pow(10, transaction.getAccount().getCurrency().getDefaultFractionDigits()))).longValue());
    }

    @AfterMapping
    void longToBigDecimalAmount(final TransactionModel transactionModel, @MappingTarget final Transaction transaction) {
        transaction.setAmount(transaction.getAmount().divide(BigDecimal.valueOf(Math.pow(10, transactionModel.getAccount().getCurrency().getDefaultFractionDigits()))));
    }
}
