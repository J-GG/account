package fr.jg.account.mappers.estate.tradingAccount;

import fr.jg.account.controllers.estate.EstateController;
import fr.jg.account.controllers.estate.tradingAccount.TradingAccountController;
import fr.jg.account.domain.estate.tradingAccount.TradingAccount;
import fr.jg.account.dto.LinkedResource;
import fr.jg.account.dto.estate.tradingAccount.TradingAccountDto;
import fr.jg.account.mappers.AbstractMapper;
import fr.jg.account.mappers.estate.EstateMapper;
import fr.jg.account.models.estate.tradingAccount.TradingAccountModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Mapper(componentModel = "spring", uses = {EstateMapper.class, TradingTransactionMapper.class, TradingWireTransactionMapper.class})
public abstract class TradingAccountMapper extends AbstractMapper<TradingAccountDto, TradingAccount, TradingAccountModel> {

    @AfterMapping
    void afterMappingDomainToDto(final TradingAccount tradingAccount, @MappingTarget final TradingAccountDto tradingAccountDto) {
        try {
            final LinkedResource<UUID> linkedEstate = new LinkedResource<>(tradingAccount.getEstate().getId());
            linkedEstate.add(linkTo(EstateController.class.getMethod("getEstate", UUID.class), tradingAccount.getEstate().getId()).withSelfRel());
            tradingAccountDto.setEstate(linkedEstate);

            tradingAccountDto.add(linkTo(TradingAccountController.class.getMethod("getTradingAccount", UUID.class), tradingAccount.getId()).withSelfRel());
        } catch (final NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }
}
