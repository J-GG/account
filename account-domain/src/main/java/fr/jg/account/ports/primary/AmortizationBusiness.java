package fr.jg.account.ports.primary;

import fr.jg.account.domain.Amortization;

public interface AmortizationBusiness {
    Amortization compute(Amortization amortization);
}
