package fr.jg.account.controllers;

import fr.jg.account.configuration.Configuration;
import fr.jg.account.dto.*;
import fr.jg.account.mappers.AmortizationMapper;
import fr.jg.account.ports.primary.AmortizationBusiness;
import fr.jg.account.services.FinancialModelingPrepClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/amortization")
public class AmortizationController {

    @Autowired
    AmortizationBusiness amortizationBusiness;

    @Autowired
    AmortizationMapper amortizationMapper;

    @Autowired
    FinancialModelingPrepClient financialModelingPrepClient;

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

        final TradingAccountDto.TransactionDto transactionDto3 = new TradingAccountDto.TransactionDto();
        transactionDto3.setId(UUID.randomUUID());
        transactionDto3.setDate(LocalDate.now());
        final TradingOperationBuyDto tradingOperationBuyDto3 = new TradingOperationBuyDto();
        tradingOperationBuyDto3.setCode("AIR.PA");
        tradingOperationBuyDto3.setQuantity(10);
        tradingOperationBuyDto3.setUnitPrice(BigDecimal.valueOf(97.32));
        tradingOperationBuyDto3.setFees(BigDecimal.valueOf(1.9));
        transactionDto3.setOperation(tradingOperationBuyDto3);
        transactionDtos.add(transactionDto3);

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
                stock.setInvestedAmount(stock.getInvestedAmount().add(operation.getUnitPrice().multiply(BigDecimal.valueOf(operation.getQuantity())).add(operation.getFees())));
                stocks.put(operation.getCode(), stock);

                tradingAccountDto.setCashValue(tradingAccountDto.getCashValue()
                        .subtract(operation.getUnitPrice()
                                .multiply(BigDecimal.valueOf(operation.getQuantity()))
                                .add(operation.getFees()))
                );
            }
        });

        tradingAccountDto.setStocks(stocks);

        if (tradingAccountDto.getStocks() != null) {
            final String codes = tradingAccountDto.getStocks().keySet().stream().collect(Collectors.joining(","));
            this.financialModelingPrepClient.findAllTimePrices(codes).getHistoricalStockList().forEach(financialModelingHistoricalStockList -> {
                final StockDto stockDto = stocks.get(financialModelingHistoricalStockList.getSymbol());
                final SortedMap<LocalDate, BigDecimal> stockHistoryDtos = financialModelingHistoricalStockList.getHistorical().stream()
                        .collect(Collectors.toMap(FinancialModelingHistoricalStockList.FinancialModelingHistoricalStock.HistoricalStockDay::getDate,
                                FinancialModelingHistoricalStockList.FinancialModelingHistoricalStock.HistoricalStockDay::getClose,
                                (price1, price2) -> price1,
                                TreeMap::new)
                        );
                stockDto.setHistory(stockHistoryDtos);
            });
            this.financialModelingPrepClient.findRealTimePrice(codes).forEach(financialModelingHistoricalStock -> {
                final StockDto stockDto = stocks.get(financialModelingHistoricalStock.getSymbol());
                stockDto.getHistory().put(LocalDate.now(), financialModelingHistoricalStock.getPrice());
            });
        }


        System.out.println("Somme iwnvestie : " + tradingAccountDto.getInvestedAmount());
        System.out.println("Somme cash : " + tradingAccountDto.getCashValue());
        System.out.println("Valeur actuelle en bourse : " + tradingAccountDto.getStockValue());
        System.out.println("Valeur actuelle : " + tradingAccountDto.getCurrentValue());
        System.out.println("Evolution : " + BigDecimal.valueOf(100).subtract(tradingAccountDto.getCurrentValue().multiply(BigDecimal.valueOf(100).divide(tradingAccountDto.getInvestedAmount(), 2, Configuration.ROUNDING_MODE))).multiply(BigDecimal.valueOf(-1)));
        System.out.println("");
        tradingAccountDto.getStocks().forEach((s, stockDto) -> {
            System.out.println(stockDto.getCode());
            System.out.println("PRU : " + stockDto.getUnitPrice());
            System.out.println("Prix actuel : " + stockDto.getCurrentPrice());
            System.out.println("Quantité : " + stockDto.getQuantity());
            System.out.println("Somme investie : " + stockDto.getInvestedAmount());
            System.out.println("Valeur actuelle : " + stockDto.getCurrentPrice().multiply(BigDecimal.valueOf(stockDto.getQuantity())));
            System.out.println("Evolution  : " + BigDecimal.valueOf(100).subtract(stockDto.getCurrentPrice().multiply(BigDecimal.valueOf(stockDto.getQuantity()))
                    .multiply(BigDecimal.valueOf(100).divide(stockDto.getInvestedAmount(), 2, Configuration.ROUNDING_MODE))).multiply(BigDecimal.valueOf(-1)));
        });

        return this.amortizationMapper.domainToDto(this.amortizationBusiness.compute(this.amortizationMapper.dtoToDomain(amortizationDto)));
    }
}
