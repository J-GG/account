package fr.jg.account.services;

import fr.jg.account.dto.FinancialModelingCompanyDto;
import fr.jg.account.dto.FinancialModelingHistoricalStockList;
import fr.jg.account.dto.FinancialModelingRealTimeStock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "Client", url = "https://financialmodelingprep.com/api/v3")
public interface FinancialModelingPrepClient {

    @GetMapping("/symbol/available-euronext?apikey=c6d8c72359d80b17ce430cc1cd0a9491")
    List<FinancialModelingCompanyDto> findAllCompanies();

    @GetMapping("/historical-price-full/{codes}?serietype=line&apikey=c6d8c72359d80b17ce430cc1cd0a9491")
    FinancialModelingHistoricalStockList findAllTimePrices(@PathVariable String codes);

    @GetMapping("/quote-short//{codes}?apikey=c6d8c72359d80b17ce430cc1cd0a9491")
    List<FinancialModelingRealTimeStock> findRealTimePrice(@PathVariable String codes);
}
