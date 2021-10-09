package com.neueda.assignment.atm;

public class WithdrawalRequest {

    private double amount;
    private String accountNumber;
    private String pin;

    public WithdrawalRequest(double amount, String accountNumber, String pin) {
        this.amount = amount;
        this.accountNumber = accountNumber;
        this.pin = pin;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
}
