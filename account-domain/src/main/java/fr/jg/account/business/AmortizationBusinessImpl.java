package fr.jg.account.business;

import fr.jg.account.annotations.AmortizationTypeEnum;
import fr.jg.account.configuration.Configuration;
import fr.jg.account.domain.Amortization;
import fr.jg.account.ports.primary.AmortizationBusiness;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AmortizationBusinessImpl implements AmortizationBusiness {

    @Override
    public Amortization compute(final Amortization amortization) {
        if (amortization.getStartingDate() == null) {
            amortization.setStartingDate(LocalDate.now());
        }

        final BigDecimal periodicRate = amortization.getInterestRate()
                .divide(BigDecimal.valueOf(amortization.getFrequency().getPaymentsPerYear() * 100), 20, Configuration.ROUNDING_MODE);

        final BigDecimal fixedPaymentAmount = amortization.getPrincipal()
                .multiply(periodicRate)
                .divide(BigDecimal.valueOf(1 - 1 / Math.pow(1 + periodicRate.doubleValue(), amortization.getNumberPayments())), 20, Configuration.ROUNDING_MODE);

        final BigDecimal fixedPrincipal = amortization.getPrincipal().divide(BigDecimal.valueOf(amortization.getNumberPayments()), 20, Configuration.ROUNDING_MODE);

        BigDecimal balance = amortization.getPrincipal();
        LocalDate date = amortization.getStartingDate();
        final List<Amortization.Payment> payments = new ArrayList<>();
        for (int i = 1; i <= amortization.getNumberPayments(); i++) {
            final Amortization.Payment payment = new Amortization.Payment();
            payment.setNth(i);
            date = date.plusMonths(12 / amortization.getFrequency().getPaymentsPerYear());
            payment.setDate(date);
            if (amortization.getType().equals(AmortizationTypeEnum.FIXED_PRINCIPAL)) {
                payment.setInterest(balance.multiply(periodicRate));
                payment.setPrincipal(fixedPrincipal);
                payment.setAmount(payment.getPrincipal().add(payment.getInterest()));
            } else {
                payment.setInterest(periodicRate.multiply(balance));
                payment.setPrincipal(fixedPaymentAmount.subtract(payment.getInterest()));
                payment.setAmount(fixedPaymentAmount);
            }
            payment.setBalance(balance.subtract(payment.getPrincipal()));
            balance = balance.subtract(payment.getPrincipal());
            payments.add(payment);
        }
        amortization.setPayments(payments);

        return amortization;
    }

}