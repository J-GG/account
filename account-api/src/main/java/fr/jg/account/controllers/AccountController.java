package fr.jg.account.controllers;

import fr.jg.account.domain.Account;
import fr.jg.account.dto.AccountDto;
import fr.jg.account.dto.TransactionDto;
import fr.jg.account.mappers.AccountMapper;
import fr.jg.account.mappers.TransactionMapper;
import fr.jg.account.ports.primary.AccountBusiness;
import fr.jg.account.ports.primary.TransactionBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountBusiness accountBusiness;

    @Autowired
    TransactionBusiness transactionBusiness;

    @Autowired
    TransactionMapper transactionMapper;

    @Autowired
    AccountMapper accountMapper;

    @GetMapping
    public CollectionModel<AccountDto> getAccounts() throws NoSuchMethodException {
        final CollectionModel<AccountDto> accountDtos = CollectionModel.of(this.accountMapper.domainToDto(this.accountBusiness.getAll()));
        accountDtos.add(linkTo(AccountController.class).withSelfRel());
        for (final AccountDto accountDto : accountDtos) {
            accountDto.add(linkTo(AccountController.class.getMethod("getTransactions", UUID.class), accountDto.getId()).withRel("transactions"));
            accountDto.add(linkTo(AccountController.class.getMethod("getAccount", UUID.class), accountDto.getId()).withSelfRel());
        }

        return accountDtos;
    }

    @PostMapping
    public AccountDto postAccount(@RequestBody final AccountDto accountDto) throws NoSuchMethodException {
        final Account account = this.accountBusiness.create(this.accountMapper.dtoToDomain(accountDto));

        return this.getAccount(account.getId());
    }

    @GetMapping("/{id}")
    public AccountDto getAccount(@PathVariable("id") final UUID accountId) throws NoSuchMethodException {
        final AccountDto account = this.accountMapper.domainToDto(this.accountBusiness.get(accountId));
        account.add(linkTo(AccountController.class.getMethod("getTransactions", UUID.class), accountId).withRel("transactions"));
        account.add(linkTo(AccountController.class.getMethod("getAccount", UUID.class), accountId).withSelfRel());

        return account;
    }

    @GetMapping("/{id}/transactions")
    public CollectionModel<TransactionDto> getTransactions(@PathVariable("id") final UUID accountId) throws NoSuchMethodException {
        final CollectionModel<TransactionDto> transactionDtos = CollectionModel.of(this.transactionMapper.domainToDto(this.transactionBusiness.getAllByAccountId(accountId)));
        for (final TransactionDto transactionDto : transactionDtos) {
            transactionDto.add(linkTo(AccountController.class.getMethod("getAccount", UUID.class), accountId).withRel("account"));
            transactionDto.add(linkTo(TransactionController.class.getMethod("getTransaction", UUID.class), transactionDto.getId()).withSelfRel());
        }
        transactionDtos.add(linkTo(AccountController.class.getMethod("getTransactions", UUID.class), accountId).withSelfRel());

        return transactionDtos;
    }

    @PostMapping("/{id}/transactions")
    public CollectionModel<TransactionDto> postTransactionToAccount(@PathVariable("id") final UUID accountId, @RequestBody final TransactionDto transactionDto) throws NoSuchMethodException {
        final Account account = this.accountBusiness.addTransactionToAccount(accountId, this.transactionMapper.dtoToDomain(transactionDto));

        return this.getTransactions(account.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable("id") final UUID accountId) {
        this.accountBusiness.delete(accountId);
    }
}