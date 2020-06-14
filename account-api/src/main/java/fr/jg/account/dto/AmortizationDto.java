package fr.jg.account.dto;

import fr.jg.account.annotations.AmortizationFrequencyEnum;
import fr.jg.account.annotations.AmortizationTypeEnum;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

@Relation(collectionRelation = "amortization")
public class AmortizationDto extends RepresentationModel<AmortizationDto> {

    private BigDecimal principal;

    private Currency currency;

    private LocalDate startingDate;

    private BigDecimal interestRate;

    private AmortizationFrequencyEnum frequency;

    private int numberPayments;

    private AmortizationTypeEnum type;

    private BigDecimal totalInterest;

    private BigDecimal totalAmount;

    private List<PaymentDto> payments;

    public BigDecimal getPrincipal() {
        return this.principal;
    }

    public void setPrincipal(final BigDecimal principal) {
        this.principal = principal;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public void setCurrency(final Currency currency) {
        this.currency = currency;
    }

    public LocalDate getStartingDate() {
        return this.startingDate;
    }

    public void setStartingDate(final LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public BigDecimal getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(final BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public AmortizationFrequencyEnum getFrequency() {
        return this.frequency;
    }

    public void setFrequency(final AmortizationFrequencyEnum frequency) {
        this.frequency = frequency;
    }

    public int getNumberPayments() {
        return this.numberPayments;
    }

    public void setNumberPayments(final int numberPayments) {
        this.numberPayments = numberPayments;
    }

    public AmortizationTypeEnum getType() {
        return this.type;
    }

    public void setType(final AmortizationTypeEnum type) {
        this.type = type;
    }

    public BigDecimal getTotalInterest() {
        return this.totalInterest;
    }

    public void setTotalInterest(final BigDecimal totalInterest) {
        this.totalInterest = totalInterest;
    }

    public BigDecimal getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(final BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<PaymentDto> getPayments() {
        return this.payments;
    }

    public void setPayments(final List<PaymentDto> payments) {
        this.payments = payments;
    }

    public static class PaymentDto {

        private int nth;

        private LocalDate date;

        private BigDecimal interest;

        private BigDecimal principal;

        private BigDecimal balance;

        private BigDecimal amount;

        public int getNth() {
            return this.nth;
        }

        public void setNth(final int nth) {
            this.nth = nth;
        }

        public LocalDate getDate() {
            return this.date;
        }

        public void setDate(final LocalDate date) {
            this.date = date;
        }

        public BigDecimal getInterest() {
            return this.interest;
        }

        public void setInterest(final BigDecimal interest) {
            this.interest = interest;
        }

        public BigDecimal getPrincipal() {
            return this.principal;
        }

        public void setPrincipal(final BigDecimal principal) {
            this.principal = principal;
        }

        public BigDecimal getBalance() {
            return this.balance;
        }

        public void setBalance(final BigDecimal balance) {
            this.balance = balance;
        }

        public BigDecimal getAmount() {
            return this.amount;
        }

        public void setAmount(final BigDecimal amount) {
            this.amount = amount;
        }
    }
}
