package fr.jg.account.annotations;

public enum AmortizationFrequencyEnum {
    ANNUAL(1),

    BI_ANNUAL(2),

    QUARTERLY(4),

    MONTHLY(12);

    private int paymentsPerYear;

    AmortizationFrequencyEnum(final int paymentsPerYear) {
        this.paymentsPerYear = paymentsPerYear;
    }

    public int getPaymentsPerYear() {
        return this.paymentsPerYear;
    }

    public void setPaymentsPerYear(final int paymentsPerYear) {
        this.paymentsPerYear = paymentsPerYear;
    }
}
