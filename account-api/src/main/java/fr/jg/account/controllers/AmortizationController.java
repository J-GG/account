package fr.jg.account.controllers;

import fr.jg.account.dto.*;
import fr.jg.account.mappers.AmortizationMapper;
import fr.jg.account.ports.primary.AmortizationBusiness;
import fr.jg.account.services.AlphaVantageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/amortization")
public class AmortizationController {

    @Autowired
    AmortizationBusiness amortizationBusiness;

    @Autowired
    AmortizationMapper amortizationMapper;

    @Autowired
    AlphaVantageClient alphaVantageClient;

    @PostMapping
    public AmortizationDto postAmortization(@RequestBody final AmortizationDto amortizationDto) {
        final TradingAccountDto tradingAccountDto = new TradingAccountDto();
        final List<TradingAccountDto.TransactionDto> transactionDtos = new ArrayList<>();
        tradingAccountDto.setTransactions(transactionDtos);

        final TradingAccountDto.TransactionDto transactionDtoWire = new TradingAccountDto.TransactionDto();
        transactionDtoWire.setId(UUID.randomUUID());
        transactionDtoWire.setDate(LocalDate.now());
        final TradingOperationWireTransferDto tradingOperationWireTransferDto = new TradingOperationWireTransferDto();
        tradingOperationWireTransferDto.setAmount(BigDecimal.valueOf(1000));
        transactionDtoWire.setOperation(tradingOperationWireTransferDto);
        transactionDtos.add(transactionDtoWire);


        final TradingAccountDto.TransactionDto transactionDto = new TradingAccountDto.TransactionDto();
        transactionDto.setId(UUID.randomUUID());
        transactionDto.setDate(LocalDate.now());
        final TradingOperationBuyDto tradingOperationBuyDto = new TradingOperationBuyDto();
        tradingOperationBuyDto.setCode("FP.PA");
        tradingOperationBuyDto.setQuantity(10);
        tradingOperationBuyDto.setUnitPrice(BigDecimal.valueOf(50));
        tradingOperationBuyDto.setFees(BigDecimal.valueOf(10));
        transactionDto.setOperation(tradingOperationBuyDto);
        transactionDtos.add(transactionDto);

        final TradingAccountDto.TransactionDto transactionDto2 = new TradingAccountDto.TransactionDto();
        transactionDto2.setId(UUID.randomUUID());
        transactionDto2.setDate(LocalDate.now());
        final TradingOperationBuyDto tradingOperationBuyDto2 = new TradingOperationBuyDto();
        tradingOperationBuyDto2.setCode("FP.PA");
        tradingOperationBuyDto2.setQuantity(5);
        tradingOperationBuyDto2.setUnitPrice(BigDecimal.valueOf(40));
        tradingOperationBuyDto2.setFees(BigDecimal.valueOf(10));
        transactionDto2.setOperation(tradingOperationBuyDto2);
        transactionDtos.add(transactionDto2);

        final Map<String, StockDto> stocks = new HashMap<>();
        tradingAccountDto.getTransactions().forEach(transaction -> {
            if (transaction.getOperation() instanceof TradingOperationWireTransferDto) {
                final TradingOperationWireTransferDto operation = (TradingOperationWireTransferDto) transaction.getOperation();
                tradingAccountDto.setInvestedAmount(tradingAccountDto.getInvestedAmount().add(operation.getAmount()));
                tradingAccountDto.setCashValue(tradingAccountDto.getCashValue().add(operation.getAmount()));
            } else if (transaction.getOperation() instanceof TradingOperationBuyDto) {
                final TradingOperationBuyDto operation = (TradingOperationBuyDto) transaction.getOperation();
                final StockDto stock = stocks.getOrDefault(operation.getCode(), new StockDto());
                stock.setCode(operation.getCode());
                stock.setQuantity(stock.getQuantity() + operation.getQuantity());
                stock.setInvestedAmount(operation.getUnitPrice().multiply(BigDecimal.valueOf(operation.getQuantity())));
                if (stock.getHistory() == null) {
                    final List<StockDto.StockHistoryDto> historyDtos = new ArrayList<>();
                    stock.setHistory(historyDtos);
                    this.alphaVantageClient.find(stock.getCode()).get("Time Series (Daily)").forEach(jsonNode -> {
                        final StockDto.StockHistoryDto historyDto = new StockDto.StockHistoryDto();
                    });
                }
                stocks.put(operation.getCode(), stock);

                tradingAccountDto.setCashValue(tradingAccountDto.getCashValue()
                        .subtract(operation.getUnitPrice().multiply(BigDecimal.valueOf(operation.getQuantity())))
                        .subtract(operation.getFees())
                );
            }
        });
        tradingAccountDto.setStocks(stocks);

        return this.amortizationMapper.domainToDto(this.amortizationBusiness.compute(this.amortizationMapper.dtoToDomain(amortizationDto)));
    }

}
