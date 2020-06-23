package fr.jg.account.mappers.estate.tradingAccount;

import fr.jg.account.controllers.estate.tradingAccount.TradingAccountController;
import fr.jg.account.controllers.estate.tradingAccount.TradingWireTransactionController;
import fr.jg.account.domain.estate.tradingAccount.TradingWireTransaction;
import fr.jg.account.dto.estate.tradingAccount.TradingWireTransactionDto;
import fr.jg.account.mappers.AbstractMapper;
import fr.jg.account.models.estate.tradingAccount.TradingWireTransactionModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Mapper(componentModel = "spring", uses = {TradingAccountMapper.class})
public abstract class TradingWireTransactionMapper extends AbstractMapper<TradingWireTransactionDto, TradingWireTransaction, TradingWireTransactionModel> {

    @AfterMapping
    void afterMappingDomainToDto(final TradingWireTransaction tradingWireTransaction, @MappingTarget final TradingWireTransactionDto tradingWireTransactionDto) {
        try {
            tradingWireTransactionDto.getTradingAccount().add(linkTo(TradingAccountController.class.getMethod("getTradingAccount", UUID.class), tradingWireTransaction.getTradingAccount().getId()).withSelfRel());

            tradingWireTransactionDto.add(linkTo(TradingWireTransactionController.class.getMethod("getWireTransactions"), tradingWireTransaction.getId()).withSelfRel());
        } catch (final NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }
}
