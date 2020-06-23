package fr.jg.account.controllers.estate.tradingAccount;

import fr.jg.account.dto.estate.tradingAccount.TradingAccountDto;
import fr.jg.account.dto.estate.tradingAccount.TradingTransactionDto;
import fr.jg.account.dto.estate.tradingAccount.TradingWireTransactionDto;
import fr.jg.account.mappers.estate.tradingAccount.TradingAccountMapper;
import fr.jg.account.mappers.estate.tradingAccount.TradingTransactionMapper;
import fr.jg.account.mappers.estate.tradingAccount.TradingWireTransactionMapper;
import fr.jg.account.ports.primary.estate.tradingAccount.TradingAccountBusiness;
import fr.jg.account.ports.primary.estate.tradingAccount.TradingTransactionBusiness;
import fr.jg.account.ports.primary.estate.tradingAccount.TradingWireTransactionBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@RequestMapping("/tradingAccounts")
public class TradingAccountController {

    @Autowired
    TradingAccountBusiness tradingAccountBusiness;

    @Autowired
    TradingAccountMapper tradingAccountMapper;

    @Autowired
    TradingTransactionBusiness tradingTransactionBusiness;

    @Autowired
    TradingTransactionMapper tradingTransactionMapper;

    @Autowired
    TradingWireTransactionBusiness tradingWireTransactionBusiness;

    @Autowired
    TradingWireTransactionMapper tradingWireTransactionMapper;


    @GetMapping
    public CollectionModel<TradingAccountDto> getTradingAccounts() {
        final CollectionModel<TradingAccountDto> tradingAccountDtos = CollectionModel.of(this.tradingAccountMapper.domainToDto(this.tradingAccountBusiness.getAll()));
        tradingAccountDtos.add(linkTo(TradingAccountController.class).withSelfRel());

        return tradingAccountDtos;
    }

    @GetMapping("/{id}")
    public TradingAccountDto getTradingAccount(@PathVariable("id") final UUID tradingAccountId) {
        return this.tradingAccountMapper.domainToDto(this.tradingAccountBusiness.get(tradingAccountId));
    }

    @GetMapping("/{id}/transactions")
    public CollectionModel<TradingTransactionDto> getTransactions(@PathVariable("id") final UUID tradingAccountId) throws NoSuchMethodException {
        return this.tradingTransactionMapper.domainToCollectionModel(this.tradingTransactionBusiness.getByTradingAccountId(tradingAccountId),
                linkTo(TradingAccountController.class.getMethod("getTradingAccount", UUID.class), tradingAccountId).withRel("tradingAccount"),
                linkTo(TradingAccountController.class.getMethod("getTransactions", UUID.class), tradingAccountId).withSelfRel()
        );
    }

    @PostMapping("/{id}/transactions")
    @ResponseStatus(HttpStatus.CREATED)
    public TradingTransactionDto postTradingTransactionToAccount(@PathVariable("id") final UUID cashAccountId, @RequestBody final TradingTransactionDto tradingTransactionDto) {
        return this.tradingTransactionMapper.domainToDto(this.tradingAccountBusiness.addTransactionToAccount(cashAccountId, this.tradingTransactionMapper.dtoToDomain(tradingTransactionDto)));
    }

    @GetMapping("/{id}/wireTransactions")
    public CollectionModel<TradingWireTransactionDto> getWireTransactions(@PathVariable("id") final UUID tradingAccountId) throws NoSuchMethodException {
        return this.tradingWireTransactionMapper.domainToCollectionModel(this.tradingWireTransactionBusiness.getByTradingAccountId(tradingAccountId),
                linkTo(TradingAccountController.class.getMethod("getTradingAccount", UUID.class), tradingAccountId).withRel("tradingAccount"),
                linkTo(TradingAccountController.class.getMethod("getWireTransactions", UUID.class), tradingAccountId).withSelfRel()
        );
    }

    @PostMapping("/{id}/wireTransactions")
    @ResponseStatus(HttpStatus.CREATED)
    public TradingWireTransactionDto postWireTransactionToAccount(@PathVariable("id") final UUID cashAccountId, @RequestBody final TradingWireTransactionDto tradingWireTransactionDto) {
        return this.tradingWireTransactionMapper.domainToDto(this.tradingAccountBusiness.addWireTransactionToAccount(cashAccountId, this.tradingWireTransactionMapper.dtoToDomain(tradingWireTransactionDto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable("id") final UUID tradingAccountId) {
        this.tradingAccountBusiness.delete(tradingAccountId);
    }
}