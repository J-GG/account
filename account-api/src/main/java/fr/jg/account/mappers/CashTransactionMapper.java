package fr.jg.account.mappers;

import fr.jg.account.controllers.CashAccountController;
import fr.jg.account.controllers.CategoryController;
import fr.jg.account.controllers.TransactionController;
import fr.jg.account.domain.CashTransaction;
import fr.jg.account.dto.CashTransactionDto;
import fr.jg.account.models.CashTransactionModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Mapper(componentModel = "spring", uses = {CashAccountMapper.class, CategoryMapper.class})
public abstract class CashTransactionMapper extends AbstractMapper<CashTransactionDto, CashTransaction, CashTransactionModel> {

    @AfterMapping
    void afterMappingDomainToModel(final CashTransaction cashTransaction, @MappingTarget final CashTransactionModel cashTransactionModel) {
        cashTransactionModel.setAmount(cashTransaction.getAmount().multiply(BigDecimal.valueOf(Math.pow(10, cashTransaction.getCashAccount().getCurrency().getDefaultFractionDigits()))).longValue());
    }

    @AfterMapping
    void afterMappingDomainToModel(final CashTransactionModel cashTransactionModel, @MappingTarget final CashTransaction cashTransaction) {
        cashTransaction.setAmount(BigDecimal.valueOf(cashTransactionModel.getAmount()).divide(BigDecimal.valueOf(Math.pow(10, cashTransactionModel.getCashAccount().getCurrency().getDefaultFractionDigits()))));
    }

    @AfterMapping
    void afterMappingDomainToDto(final CashTransaction cashTransaction, @MappingTarget final CashTransactionDto cashTransactionDto) {
        try {
            cashTransactionDto.getCashAccount().add(linkTo(CashAccountController.class.getMethod("getCashAccount", UUID.class), cashTransactionDto.getCashAccount().getId()).withSelfRel());
            if (cashTransaction.getCategory() != null) {
                cashTransactionDto.getCategory().add(linkTo(CategoryController.class.getMethod("getCategory", Long.class), cashTransactionDto.getCategory().getId()).withSelfRel());
            }
            cashTransactionDto.add(linkTo(TransactionController.class.getMethod("getTransaction", UUID.class), cashTransaction.getId()).withSelfRel());
        } catch (final NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }
}
