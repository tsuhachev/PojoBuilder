package ua.testing.builder.domain;

import java.time.LocalDate;

/**
 * @author timofey.sukhachev
 */
public enum PaymentEnum {

    PURCHASE_OF_BOOK(Billing.JOHN_DOE, LocalDate.of(2016, 2, 2));

    private final LocalDate date;
    private final Billing billingDetails;

    PaymentEnum(Billing billing, LocalDate date) {
        this.billingDetails = billing;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public Billing getBillingDetails() {
        return billingDetails;
    }
}
