package fr.jg.account.controllers;

import fr.jg.account.domain.Transaction;
import fr.jg.account.dto.TransactionDto;
import fr.jg.account.mappers.TransactionMapper;
import fr.jg.account.ports.primary.TransactionBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionBusiness transactionBusiness;

    @Autowired
    TransactionMapper transactionMapper;

    @GetMapping
    public CollectionModel<TransactionDto> getTransactions() throws NoSuchMethodException {
        final CollectionModel<TransactionDto> transactionDtos = CollectionModel.of(this.transactionMapper.domainToDto(this.transactionBusiness.getAll()));
        transactionDtos.add(linkTo(TransactionController.class.getMethod("getTransactions")).withSelfRel());

        return transactionDtos;
    }

    @GetMapping("/{id}")
    public TransactionDto getTransaction(@PathVariable("id") final UUID transactionId) throws NoSuchMethodException {
        final Transaction transaction = this.transactionBusiness.get(transactionId);
        final TransactionDto transactionDto = this.transactionMapper.domainToDto(transaction);
        transactionDto.add(linkTo(AccountController.class.getMethod("getAccount", UUID.class), transaction.getAccount().getId()).withSelfRel());
        transactionDto.add(linkTo(TransactionController.class.getMethod("getTransaction", UUID.class), transactionId).withSelfRel());

        return transactionDto;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransaction(@PathVariable("id") final UUID transactionId) {
        this.transactionBusiness.delete(transactionId);
    }
}