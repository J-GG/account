package fr.jg.account.mappers.estate.cashAccount;

import fr.jg.account.configuration.Configuration;
import fr.jg.account.controllers.estate.EstateController;
import fr.jg.account.controllers.estate.cashAccount.CashAccountController;
import fr.jg.account.domain.estate.cashAccount.CashAccount;
import fr.jg.account.dto.LinkedResource;
import fr.jg.account.dto.LinkedResourceArray;
import fr.jg.account.dto.estate.cashAccount.CashAccountDto;
import fr.jg.account.mappers.AbstractMapper;
import fr.jg.account.mappers.estate.EstateMapper;
import fr.jg.account.models.estate.cashAccount.CashAccountModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Mapper(componentModel = "spring", uses = {CashTransactionMapper.class, EstateMapper.class})
public abstract class CashAccountMapper extends AbstractMapper<CashAccountDto, CashAccount, CashAccountModel> {

    @AfterMapping
    void afterMappingDomainToModel(final CashAccount cashAccount, @MappingTarget final CashAccountModel cashAccountModel) {
        cashAccountModel.setYieldRate(cashAccount.getYieldRate().multiply(BigDecimal.valueOf(Math.pow(10, Configuration.INTERESTED_RATES_PRECISION))).longValue());
    }

    @AfterMapping
    void afterMappingModelToDomain(final CashAccountModel cashAccountModel, @MappingTarget final CashAccount cashAccount) {
        cashAccount.setYieldRate(BigDecimal.valueOf(cashAccountModel.getYieldRate()).divide(BigDecimal.valueOf(Math.pow(10, Configuration.INTERESTED_RATES_PRECISION))));
    }

    @AfterMapping
    void afterMappingDomainToDto(final CashAccount cashAccount, @MappingTarget final CashAccountDto cashAccountDto) {
        try {
            final LinkedResourceArray linkedTransactions = new LinkedResourceArray(cashAccount.getTransactions().size());
            linkedTransactions.add(linkTo(CashAccountController.class.getMethod("getCashTransactions", UUID.class), cashAccount.getId()).withSelfRel());
            cashAccountDto.setTransactions(linkedTransactions);

            final LinkedResource<UUID> linkedEstate = new LinkedResource<>(cashAccount.getEstate().getId());
            linkedEstate.add(linkTo(EstateController.class.getMethod("getEstate", UUID.class), cashAccount.getEstate().getId()).withSelfRel());
            cashAccountDto.setEstate(linkedEstate);

            cashAccountDto.add(linkTo(CashAccountController.class.getMethod("getCashAccount", UUID.class), cashAccount.getId()).withSelfRel());
        } catch (final NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }
}
