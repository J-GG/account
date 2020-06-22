package fr.jg.account.controllers.estate.cashAccount;

import fr.jg.account.dto.estate.cashAccount.CashTransactionDto;
import fr.jg.account.mappers.estate.cashAccount.CashTransactionMapper;
import fr.jg.account.ports.primary.estate.cashAccount.CashTransactionBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/transactions")
public class CashTransactionController {

    @Autowired
    private CashTransactionBusiness cashTransactionBusiness;

    @Autowired
    private CashTransactionMapper cashTransactionMapper;

    @GetMapping
    public CollectionModel<CashTransactionDto> getTransactions() {
        final CollectionModel<CashTransactionDto> transactionDtos = CollectionModel.of(this.cashTransactionMapper.domainToDto(this.cashTransactionBusiness.getAll()));
        transactionDtos.add(linkTo(CashTransactionController.class).withSelfRel());

        return transactionDtos;
    }

    @GetMapping("/{id}")
    public CashTransactionDto getTransaction(@PathVariable("id") final UUID transactionId) {
        return this.cashTransactionMapper.domainToDto(this.cashTransactionBusiness.get(transactionId));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransaction(@PathVariable("id") final UUID transactionId) {
        this.cashTransactionBusiness.delete(transactionId);
    }
}