package ua.testing.builder.domain;

import java.time.LocalDate;

/**
 * @author timofey.sukhachev
 */
public enum Card {

    VISA("134134135", LocalDate.of(2016, 11, 11));

    Card(String number, LocalDate date) {
        this.number = number;
        this.expDate = date;
    }

    private String number;

    private LocalDate expDate;

    public String getNumber() {
        return number;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

}
