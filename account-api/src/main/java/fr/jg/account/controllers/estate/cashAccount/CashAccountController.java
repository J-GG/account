package fr.jg.account.controllers.estate.cashAccount;

import fr.jg.account.dto.estate.cashAccount.CashAccountDto;
import fr.jg.account.dto.estate.cashAccount.CashTransactionDto;
import fr.jg.account.mappers.estate.cashAccount.CashAccountMapper;
import fr.jg.account.mappers.estate.cashAccount.CashTransactionMapper;
import fr.jg.account.ports.primary.estate.cashAccount.CashAccountBusiness;
import fr.jg.account.ports.primary.estate.cashAccount.CashTransactionBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@RequestMapping("/cashAccounts")
public class CashAccountController {

    @Autowired
    CashAccountBusiness cashAccountBusiness;

    @Autowired
    CashTransactionBusiness cashTransactionBusiness;

    @Autowired
    CashTransactionMapper cashTransactionMapper;

    @Autowired
    CashAccountMapper cashAccountMapper;

    @GetMapping
    public CollectionModel<CashAccountDto> getCashAccounts() {
        return this.cashAccountMapper.domainToCollectionModel(this.cashAccountBusiness.getAll(), linkTo(CashAccountController.class).withSelfRel());
    }

    @GetMapping("/{id}")
    public CashAccountDto getCashAccount(@PathVariable("id") final UUID cashAccountId) {
        return this.cashAccountMapper.domainToDto(this.cashAccountBusiness.get(cashAccountId));
    }

    @GetMapping("/{id}/transactions")
    public CollectionModel<CashTransactionDto> getCashTransactions(@PathVariable("id") final UUID cashAccountId) throws NoSuchMethodException {
        return this.cashTransactionMapper.domainToCollectionModel(this.cashTransactionBusiness.getByCashAccountId(cashAccountId),
                linkTo(CashAccountController.class.getMethod("getCashAccount", UUID.class), cashAccountId).withRel("cashAccount"),
                linkTo(CashAccountController.class.getMethod("getCashTransactions", UUID.class), cashAccountId).withSelfRel());
    }

    @PostMapping("/{id}/transactions")
    @ResponseStatus(HttpStatus.CREATED)
    public CashTransactionDto postCashTransactionToAccount(@PathVariable("id") final UUID cashAccountId, @RequestBody final CashTransactionDto cashTransactionDto) {
        return this.cashTransactionMapper.domainToDto(this.cashAccountBusiness.addTransactionToAccount(cashAccountId, this.cashTransactionMapper.dtoToDomain(cashTransactionDto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCashAccount(@PathVariable("id") final UUID cashAccountId) {
        this.cashAccountBusiness.delete(cashAccountId);
    }
}