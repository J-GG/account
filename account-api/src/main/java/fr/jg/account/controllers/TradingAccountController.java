package fr.jg.account.controllers;

import fr.jg.account.dto.CashAccountDto;
import fr.jg.account.dto.CashTransactionDto;
import fr.jg.account.mappers.CashAccountMapper;
import fr.jg.account.mappers.CashTransactionMapper;
import fr.jg.account.ports.primary.CashAccountBusiness;
import fr.jg.account.ports.primary.CashTransactionBusiness;
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
    CashAccountBusiness cashAccountBusiness;

    @Autowired
    CashTransactionBusiness cashTransactionBusiness;

    @Autowired
    CashTransactionMapper cashTransactionMapper;

    @Autowired
    CashAccountMapper cashAccountMapper;

    @GetMapping
    public CollectionModel<CashAccountDto> getTradingAccounts() {
        final CollectionModel<CashAccountDto> tradingAccountDtos = CollectionModel.of(this.cashAccountMapper.domainToDto(this.cashAccountBusiness.getAll()));
        tradingAccountDtos.add(linkTo(TradingAccountController.class).withSelfRel());

        return tradingAccountDtos;
    }

    @GetMapping("/{id}")
    public CashAccountDto getTradingAccount(@PathVariable("id") final UUID tradingAccountId) {
        return this.cashAccountMapper.domainToDto(this.cashAccountBusiness.get(tradingAccountId));
    }

    @GetMapping("/{id}/transactions")
    public CollectionModel<CashTransactionDto> getTransactions(@PathVariable("id") final UUID accountId) throws NoSuchMethodException {
        final CollectionModel<CashTransactionDto> transactionDtos = CollectionModel.of(this.cashTransactionMapper.domainToDto(this.cashTransactionBusiness.getByCashAccountId(accountId)));
        transactionDtos.add(linkTo(TradingAccountController.class.getMethod("getTradingAccount", UUID.class), accountId).withRel("account"));
        transactionDtos.add(linkTo(TradingAccountController.class.getMethod("getTransactions", UUID.class), accountId).withSelfRel());

        return transactionDtos;
    }

//    @PostMapping("/{id}/transactions")
//    @ResponseStatus(HttpStatus.CREATED)
//    public CollectionModel<CashTransactionDto> postTransactionToAccount(@PathVariable("id") final UUID tradingAccountId, @RequestBody final CashTransactionDto cashTransactionDto) throws NoSuchMethodException {
//        final CashAccount cashAccount = this.cashAccountBusiness.addTransactionToAccount(cashAccountId, this.cashTransactionMapper.dtoToDomain(cashTransactionDto));
//
//        return this.getTransactions(cashAccount.getId());
//    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable("id") final UUID accountId) {
        this.cashAccountBusiness.delete(accountId);
    }
}