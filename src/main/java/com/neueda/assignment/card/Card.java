package com.neueda.assignment.card;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Card {

    @Id
    private Long id;
    private String accountNumber;
    private String pin;
    private double balance;
    private double overdraft;

    public Card() {
    }

    public Card(Long id, String accountNumber, String pin, double balance, double overdraft) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
        this.overdraft = overdraft;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
    }

    public double maximumAmountToWithdraw(){
        return getBalance() + getOverdraft();
    }
    public double calculateBalanceAfterWithdraw(double withDrawAmount){
        return getBalance() - withDrawAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Double.compare(card.balance, balance) == 0 && Double.compare(card.overdraft, overdraft) == 0 && Objects.equals(accountNumber, card.accountNumber) && Objects.equals(pin, card.pin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, pin, balance, overdraft);
    }

    @Override
    public String toString() {
        return "User{" +
                "accountNumber='" + accountNumber + '\'' +
                ", pin='" + pin + '\'' +
                ", balance=" + balance +
                ", overdraft=" + overdraft +
                '}';
    }
}
