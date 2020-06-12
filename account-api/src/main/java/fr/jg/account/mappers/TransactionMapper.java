package fr.jg.account.mappers;

import fr.jg.account.controllers.AccountController;
import fr.jg.account.controllers.CategoryController;
import fr.jg.account.controllers.TransactionController;
import fr.jg.account.domain.Transaction;
import fr.jg.account.dto.TransactionDto;
import fr.jg.account.models.TransactionModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Mapper(componentModel = "spring", uses = {AccountMapper.class, CategoryMapper.class})
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
    void afterMappingDomainToModel(final Transaction transaction, @MappingTarget final TransactionModel transactionModel) {
        transactionModel.setAmount(transaction.getAmount().multiply(BigDecimal.valueOf(Math.pow(10, transaction.getAccount().getCurrency().getDefaultFractionDigits()))).longValue());
    }

    @AfterMapping
    void afterMappingDomainToModel(final TransactionModel transactionModel, @MappingTarget final Transaction transaction) {
        transaction.setAmount(new BigDecimal(transactionModel.getAmount()).divide(BigDecimal.valueOf(Math.pow(10, transactionModel.getAccount().getCurrency().getDefaultFractionDigits()))));
    }

    @AfterMapping
    void afterMappingDomainToDto(final Transaction transaction, @MappingTarget final TransactionDto transactionDto) {
        try {
            transactionDto.getAccount().add(linkTo(AccountController.class.getMethod("getAccount", UUID.class), transactionDto.getAccount().getId()).withSelfRel());
            if (transaction.getCategory() != null) {
                transactionDto.getCategory().add(linkTo(CategoryController.class.getMethod("getCategory", Long.class), transactionDto.getCategory().getId()).withSelfRel());
            }
            transactionDto.add(linkTo(TransactionController.class.getMethod("getTransaction", UUID.class), transaction.getId()).withSelfRel());
        } catch (final NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }
}
