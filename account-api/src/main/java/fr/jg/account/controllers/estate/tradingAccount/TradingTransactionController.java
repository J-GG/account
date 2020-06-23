package fr.jg.account.controllers.estate.tradingAccount;

import fr.jg.account.dto.estate.tradingAccount.TradingTransactionDto;
import fr.jg.account.mappers.estate.tradingAccount.TradingTransactionMapper;
import fr.jg.account.ports.primary.estate.tradingAccount.TradingTransactionBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/tradingTransactions")
public class TradingTransactionController {

    @Autowired
    private TradingTransactionBusiness tradingTransactionBusiness;

    @Autowired
    private TradingTransactionMapper tradingTransactionMapper;

    @GetMapping
    public CollectionModel<TradingTransactionDto> getTransactions() {
        return this.tradingTransactionMapper.domainToCollectionModel(this.tradingTransactionBusiness.getAll(), linkTo(TradingTransactionController.class).withSelfRel());
    }

    @GetMapping("/{id}")
    public TradingTransactionDto getTransaction(@PathVariable("id") final UUID transactionId) {
        return this.tradingTransactionMapper.domainToDto(this.tradingTransactionBusiness.get(transactionId));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransaction(@PathVariable("id") final UUID transactionId) {
        this.tradingTransactionBusiness.delete(transactionId);
    }
}