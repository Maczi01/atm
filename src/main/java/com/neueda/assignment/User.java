package com.neueda.assignment;

import java.util.Objects;

public class User {

    private String accountNumber;
    private String pin;
    private double balance;
    private double overdraft;

    public User(String accountNumber, String pin, double balance, double overdraft) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
        this.overdraft = overdraft;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Double.compare(user.balance, balance) == 0 && Double.compare(user.overdraft, overdraft) == 0 && Objects.equals(accountNumber, user.accountNumber) && Objects.equals(pin, user.pin);
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
