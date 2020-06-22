package fr.jg.account.controllers.estate.tradingAccount;

import fr.jg.account.dto.estate.tradingAccount.StockDto;
import fr.jg.account.mappers.estate.tradingAccount.StockMapper;
import fr.jg.account.ports.primary.estate.tradingAccount.StockBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    StockBusiness stockBusiness;

    @Autowired
    StockMapper stockMapper;

    @GetMapping
    public CollectionModel<StockDto> getStocks() {
        final CollectionModel<StockDto> stockDtos = CollectionModel.of(this.stockMapper.domainToDto(this.stockBusiness.getAll()));
        stockDtos.add(linkTo(StockController.class).withSelfRel());

        return stockDtos;
    }

    @GetMapping("/{id}")
    public StockDto getStock(@PathVariable("id") final String stockId) {
        return this.stockMapper.domainToDto(this.stockBusiness.get(stockId));
    }
}