package fr.jg.account.services.estate.tradingAccount;

import fr.jg.account.dto.estate.tradingAccount.financialmodeling.FinancialModelingCompanyDto;
import fr.jg.account.dto.estate.tradingAccount.financialmodeling.FinancialModelingHistoricalDividendList;
import fr.jg.account.dto.estate.tradingAccount.financialmodeling.FinancialModelingHistoricalStockList;
import fr.jg.account.dto.estate.tradingAccount.financialmodeling.FinancialModelingRealTimeStock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "Client", url = "https://financialmodelingprep.com/api/v3")
public interface FinancialModelingPrepClient {

    @GetMapping("/symbol/available-euronext?apikey=d23c81935b2e195b9c2d0bd2bce1efce")
    List<FinancialModelingCompanyDto> findAllStocks();

    @GetMapping("/historical-price-full/stock_dividend/{ids}?apikey=d23c81935b2e195b9c2d0bd2bce1efce")
    FinancialModelingHistoricalDividendList findStockDividends(@PathVariable String ids);

    @GetMapping("/historical-price-full/{ids}?serietype=line&apikey=d23c81935b2e195b9c2d0bd2bce1efce")
    FinancialModelingHistoricalStockList findAllTimePrices(@PathVariable String ids);

    @GetMapping("/quote-short//{ids}?apikey=d23c81935b2e195b9c2d0bd2bce1efce")
    List<FinancialModelingRealTimeStock> findRealTimePrice(@PathVariable String ids);
}
