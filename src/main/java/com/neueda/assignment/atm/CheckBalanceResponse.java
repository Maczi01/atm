package com.neueda.assignment.atm;

public class CheckBalanceResponse {

    private double currentBalance;
    private double maximumAmountToWithdrawal;

    public CheckBalanceResponse(double currentBalance, double maximumAmountToWithdrawal) {
        this.currentBalance = currentBalance;
        this.maximumAmountToWithdrawal = maximumAmountToWithdrawal;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public double getMaximumAmountToWithdrawal() {
        return maximumAmountToWithdrawal;
    }

    public void setMaximumAmountToWithdrawal(double maximumAmountToWithdrawal) {
        this.maximumAmountToWithdrawal = maximumAmountToWithdrawal;
    }
}
