package ua.testing.builder.domain;

/**
 * @author timofey.sukhachev
 */
public enum Billing {

    JOHN_DOE("John Doe", Card.VISA);
    // todo: support for bank account
//    JOE_SMITH("Joe Smith", BankAccount);

    Billing(String owner, Card card) {
        this.owner = owner;
        this.card = card;
    }

    private String owner;

    private Card card;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
