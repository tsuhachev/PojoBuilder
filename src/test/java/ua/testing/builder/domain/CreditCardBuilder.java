package ua.testing.builder.domain;

import ua.testing.builder.AbstractBuilder;

import java.time.LocalDate;

public class CreditCardBuilder extends AbstractBuilder<CreditCard> {

    private String number;
    private LocalDate expDate;

    public CreditCardBuilder setNumber(String number) {
        this.number = number;
        return this;
    }

    public CreditCardBuilder setExpDate(LocalDate expDate) {
        this.expDate = expDate;
        return this;
    }

}