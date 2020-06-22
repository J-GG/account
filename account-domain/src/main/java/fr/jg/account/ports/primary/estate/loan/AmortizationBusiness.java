package fr.jg.account.ports.primary.estate.loan;

import fr.jg.account.domain.estate.loan.Amortization;

public interface AmortizationBusiness {
    Amortization compute(Amortization amortization);
}
