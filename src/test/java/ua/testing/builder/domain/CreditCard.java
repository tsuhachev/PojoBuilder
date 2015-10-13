package ua.testing.builder.domain;

import java.time.LocalDate;

/**
 * @author timofey.sukhachev
 */
public class CreditCard extends BillingDetails {

    private String number;

    private LocalDate expDate;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }
}
