package com.neueda.assignment.atm;

import java.util.List;

public class WithdrawalResponse {

    private double balance;
    private List<Money> withdrawal;

    public WithdrawalResponse() {
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Money> getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(List<Money> withdrawal) {
        this.withdrawal = withdrawal;
    }

    @Override
    public String toString() {
        return "WithdrawalResponse{" +
                "balance=" + balance +
                ", withdrawal=" + withdrawal +
                '}';
    }
}
