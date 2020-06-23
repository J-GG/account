package fr.jg.account.controllers.estate.tradingAccount;

import fr.jg.account.dto.estate.tradingAccount.TradingWireTransactionDto;
import fr.jg.account.mappers.estate.tradingAccount.TradingWireTransactionMapper;
import fr.jg.account.ports.primary.estate.tradingAccount.TradingWireTransactionBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/tradingWireTransactions")
public class TradingWireTransactionController {

    @Autowired
    private TradingWireTransactionBusiness tradingWireTransactionBusiness;

    @Autowired
    private TradingWireTransactionMapper tradingWireTransactionMapper;

    @GetMapping
    public CollectionModel<TradingWireTransactionDto> getWireTransactions() {
        return this.tradingWireTransactionMapper.domainToCollectionModel(this.tradingWireTransactionBusiness.getAll(), linkTo(TradingWireTransactionController.class).withSelfRel());
    }

    @GetMapping("/{id}")
    public TradingWireTransactionDto getWireTransaction(@PathVariable("id") final UUID transactionId) {
        return this.tradingWireTransactionMapper.domainToDto(this.tradingWireTransactionBusiness.get(transactionId));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransaction(@PathVariable("id") final UUID transactionId) {
        this.tradingWireTransactionBusiness.delete(transactionId);
    }
}