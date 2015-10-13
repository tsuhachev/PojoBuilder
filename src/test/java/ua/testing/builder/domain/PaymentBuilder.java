package ua.testing.builder.domain;

import ua.testing.builder.AbstractBuilder;

import javax.inject.Inject;
import java.time.LocalDate;

public class PaymentBuilder extends AbstractBuilder<Payment> {

    private BillingDetails billingDetails;
    private LocalDate date;

    @Inject
    private CreditCardBuilder creditCardBuilder;

    public PaymentBuilder setBillingDetails(BillingDetails billingDetails) {
        this.billingDetails = billingDetails;
        return this;
    }

    public PaymentBuilder setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public Payment build(PaymentEnum paymentEnum) {
        billingDetails = creditCardBuilder.build(paymentEnum.getBillingDetails());
        return build();
    }
}