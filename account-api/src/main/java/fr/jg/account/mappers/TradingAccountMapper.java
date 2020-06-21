package fr.jg.account.mappers;

import fr.jg.account.controllers.TradingAccountController;
import fr.jg.account.domain.TradingAccount;
import fr.jg.account.dto.TradingAccountDto;
import fr.jg.account.models.TradingAccountModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Mapper(componentModel = "spring", uses = {EstateMapper.class})
public abstract class TradingAccountMapper extends AbstractMapper<TradingAccountDto, TradingAccount, TradingAccountModel> {

    @AfterMapping
    void afterMappingDomainToDto(final TradingAccount tradingAccount, @MappingTarget final TradingAccountDto tradingAccountDto) {
        try {
            tradingAccountDto.add(linkTo(TradingAccountController.class.getMethod("getTradingAccount", UUID.class), tradingAccount.getId()).withSelfRel());
        } catch (final NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }
}
