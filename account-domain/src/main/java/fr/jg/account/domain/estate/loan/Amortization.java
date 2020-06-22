package fr.jg.account.domain.estate.loan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

public class Amortization {

    private BigDecimal principal;

    private Currency currency;

    private LocalDate startingDate;

    private BigDecimal interestRate;

    private AmortizationFrequencyEnum frequency;

    private int numberPayments;

    private AmortizationTypeEnum type;

    private List<Payment> payments;

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

    public BigDecimal getTotalAmount() {
        return this.payments.stream().map(Payment::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalInterest() {
        return this.payments.stream().map(Payment::getInterest).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Payment> getPayments() {
        return this.payments;
    }

    public void setPayments(final List<Payment> payments) {
        this.payments = payments;
    }

    public static class Payment {

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

