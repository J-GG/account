package fr.jg.account.controllers.estate.loan;

import fr.jg.account.configuration.Configuration;
import fr.jg.account.dto.estate.loan.AmortizationDto;
import fr.jg.account.dto.estate.tradingAccount.*;
import fr.jg.account.mappers.estate.loan.AmortizationMapper;
import fr.jg.account.ports.primary.estate.loan.AmortizationBusiness;
import fr.jg.account.services.estate.tradingAccount.FinancialModelingPrepClient;
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

        final Map<String, PortfolioStockDto> stocks = new HashMap<>();
        tradingAccountDto.getTransactions().forEach(transaction -> {
            if (transaction.getOperation() instanceof TradingOperationWireTransferDto) {
                final TradingOperationWireTransferDto operation = (TradingOperationWireTransferDto) transaction.getOperation();
                tradingAccountDto.setInvestedAmount(tradingAccountDto.getInvestedAmount().add(operation.getAmount()));
                tradingAccountDto.setCashValue(tradingAccountDto.getCashValue().add(operation.getAmount()));
            } else if (transaction.getOperation() instanceof TradingOperationBuyDto) {
                final TradingOperationBuyDto operation = (TradingOperationBuyDto) transaction.getOperation();
                final PortfolioStockDto stock = stocks.getOrDefault(operation.getCode(), new PortfolioStockDto());
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

        tradingAccountDto.setPortfolio(stocks);

        if (tradingAccountDto.getPortfolio() != null) {
            final String codes = tradingAccountDto.getPortfolio().keySet().stream().collect(Collectors.joining(","));
            this.financialModelingPrepClient.findAllTimePrices(codes).getHistoricalStockList().forEach(financialModelingHistoricalStockList -> {
                final PortfolioStockDto portfolioStockDto = stocks.get(financialModelingHistoricalStockList.getSymbol());
                final SortedMap<LocalDate, BigDecimal> stockHistoryDtos = financialModelingHistoricalStockList.getHistorical().stream()
                        .collect(Collectors.toMap(FinancialModelingHistoricalStockList.FinancialModelingHistoricalStock.HistoricalStockDay::getDate,
                                FinancialModelingHistoricalStockList.FinancialModelingHistoricalStock.HistoricalStockDay::getClose,
                                (price1, price2) -> price1,
                                TreeMap::new)
                        );
                portfolioStockDto.setHistory(stockHistoryDtos);
            });
            this.financialModelingPrepClient.findRealTimePrice(codes).forEach(financialModelingHistoricalStock -> {
                final PortfolioStockDto portfolioStockDto = stocks.get(financialModelingHistoricalStock.getSymbol());
                portfolioStockDto.getHistory().put(LocalDate.now(), financialModelingHistoricalStock.getPrice());
            });
        }


        System.out.println("Somme iwnvestie : " + tradingAccountDto.getInvestedAmount());
        System.out.println("Somme cash : " + tradingAccountDto.getCashValue());
        System.out.println("Valeur actuelle en bourse : " + tradingAccountDto.getStockValue());
        System.out.println("Valeur actuelle : " + tradingAccountDto.getTotalValue());
        System.out.println("Evolution : " + BigDecimal.valueOf(100).subtract(tradingAccountDto.getTotalValue().multiply(BigDecimal.valueOf(100).divide(tradingAccountDto.getInvestedAmount(), 2, Configuration.ROUNDING_MODE))).multiply(BigDecimal.valueOf(-1)));
        System.out.println("");
        tradingAccountDto.getPortfolio().forEach((s, oldStockDto) -> {
            System.out.println(oldStockDto.getCode());
            System.out.println("PRU : " + oldStockDto.getUnitPrice());
            System.out.println("Prix actuel : " + oldStockDto.getCurrentPrice());
            System.out.println("Quantité : " + oldStockDto.getQuantity());
            System.out.println("Somme investie : " + oldStockDto.getInvestedAmount());
            System.out.println("Valeur actuelle : " + oldStockDto.getCurrentPrice().multiply(BigDecimal.valueOf(oldStockDto.getQuantity())));
            System.out.println("Evolution  : " + BigDecimal.valueOf(100).subtract(oldStockDto.getCurrentPrice().multiply(BigDecimal.valueOf(oldStockDto.getQuantity()))
                    .multiply(BigDecimal.valueOf(100).divide(oldStockDto.getInvestedAmount(), 2, Configuration.ROUNDING_MODE))).multiply(BigDecimal.valueOf(-1)));
        });

        return this.amortizationMapper.domainToDto(this.amortizationBusiness.compute(this.amortizationMapper.dtoToDomain(amortizationDto)));
    }
}
