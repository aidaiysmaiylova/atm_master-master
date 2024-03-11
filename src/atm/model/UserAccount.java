package atm.model;

import java.util.Objects;

public class UserAccount {
    private String name;
    private String lastName;
    private String cardNumber;
    private String pinCode;
    private int balance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPinCode() {

        return pinCode;
    }

    public void setPinCode(String pinCode) {

        this.pinCode = pinCode;
    }

    public int getBalance() {

        return balance;
    }

    public void setBalance(int balance) {
        this.balance=balance;
    }

    @Override
    public String toString() {
        return " Name : " + name + '\'' +
                " Surname : " + lastName + '\'' +
                " Card number : " + cardNumber + '\'' +
                " Pin code : " + pinCode + '\'' +
                " Balance : " + balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return balance == that.balance && Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName) && Objects.equals(cardNumber, that.cardNumber) && Objects.equals(pinCode, that.pinCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, cardNumber, pinCode, balance);
    }
}
