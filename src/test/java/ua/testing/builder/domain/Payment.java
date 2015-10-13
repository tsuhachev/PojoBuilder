package ua.testing.builder.domain;

import java.time.LocalDate;

/**
 * @author timofey.sukhachev
 */
public class Payment {

    public BillingDetails billingDetails;

    public LocalDate date;

    public BillingDetails getBillingDetails() {
        return billingDetails;
    }

    public void setBillingDetails(BillingDetails billingDetails) {
        this.billingDetails = billingDetails;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
