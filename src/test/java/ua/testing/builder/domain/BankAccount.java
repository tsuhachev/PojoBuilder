package ua.testing.builder.domain;

/**
 * @author timofey.sukhachev
 */
public class BankAccount extends BillingDetails {

    private String account;

    private String bankName;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
