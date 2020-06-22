package fr.jg.account.mappers.estate.tradingAccount;

import fr.jg.account.domain.estate.tradingAccount.TradingTransaction;
import fr.jg.account.dto.estate.tradingAccount.TradingTransactionDto;
import fr.jg.account.mappers.AbstractMapper;
import fr.jg.account.models.estate.tradingAccount.TradingTransactionModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TradingAccountMapper.class, StockMapper.class})
public abstract class TradingTransactionMapper extends AbstractMapper<TradingTransactionDto, TradingTransaction, TradingTransactionModel> {

}
