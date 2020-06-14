package fr.jg.account.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Client", url = "https://www.alphavantage.co")
public interface AlphaVantageClient {

    @GetMapping("/query?function=TIME_SERIES_DAILY&symbol={code}&outputsize=full&apikey=J61UYXBON9NBIHE9")
    JsonNode find(@PathVariable String code);
}
