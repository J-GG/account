package fr.jg.account.mappers.estate.tradingAccount;

import fr.jg.account.domain.estate.tradingAccount.TradingWireTransaction;
import fr.jg.account.dto.estate.tradingAccount.TradingWireTransactionDto;
import fr.jg.account.mappers.AbstractMapper;
import fr.jg.account.models.estate.tradingAccount.TradingWireTransactionModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TradingAccountMapper.class})
public abstract class TradingWireTransactionMapper extends AbstractMapper<TradingWireTransactionDto, TradingWireTransaction, TradingWireTransactionModel> {

}
