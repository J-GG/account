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
@RequestMapping("/cashTransactions")
public class CashTransactionController {

    @Autowired
    private CashTransactionBusiness cashTransactionBusiness;

    @Autowired
    private CashTransactionMapper cashTransactionMapper;

    @GetMapping
    public CollectionModel<CashTransactionDto> getTransactions() {
        return this.cashTransactionMapper.domainToCollectionModel(this.cashTransactionBusiness.getAll(), linkTo(CashTransactionController.class).withSelfRel());
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