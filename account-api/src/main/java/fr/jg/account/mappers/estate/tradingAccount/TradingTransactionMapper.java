package fr.jg.account.mappers.estate.tradingAccount;

import fr.jg.account.controllers.estate.tradingAccount.TradingAccountController;
import fr.jg.account.controllers.estate.tradingAccount.TradingTransactionController;
import fr.jg.account.domain.estate.tradingAccount.TradingTransaction;
import fr.jg.account.dto.estate.tradingAccount.TradingTransactionDto;
import fr.jg.account.mappers.AbstractMapper;
import fr.jg.account.models.estate.tradingAccount.TradingTransactionModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Mapper(componentModel = "spring", uses = {TradingAccountMapper.class, StockMapper.class})
public abstract class TradingTransactionMapper extends AbstractMapper<TradingTransactionDto, TradingTransaction, TradingTransactionModel> {

    @AfterMapping
    void afterMappingDomainToDto(final TradingTransaction tradingTransaction, @MappingTarget final TradingTransactionDto tradingTransactionDto) {
        try {
            tradingTransactionDto.getTradingAccount().add(linkTo(TradingAccountController.class.getMethod("getTradingAccount", UUID.class), tradingTransaction.getTradingAccount().getId()).withSelfRel());

            tradingTransactionDto.add(linkTo(TradingTransactionController.class.getMethod("getTransaction", UUID.class), tradingTransaction.getId()).withSelfRel());
        } catch (final NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }
}
