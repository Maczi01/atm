package com.neueda.assignment.atm;

public class WithdrawalResponse {

    private double balance;
    private int fifties;
    private int twenties;
    private int tens;
    private int fives;

    public WithdrawalResponse() {
    }

    public WithdrawalResponse(double balance, int fifties, int twenties, int tens, int fives) {
        this.balance = balance;
        this.fifties = fifties;
        this.twenties = twenties;
        this.tens = tens;
        this.fives = fives;
    }

    public int getFifties() {
        return fifties;
    }

    public void setFifties(int fifties) {
        this.fifties = fifties;
    }

    public int getTwenties() {
        return twenties;
    }

    public void setTwenties(int twenties) {
        this.twenties = twenties;
    }

    public int getTens() {
        return tens;
    }

    public void setTens(int tens) {
        this.tens = tens;
    }

    public int getFives() {
        return fives;
    }

    public void setFives(int fives) {
        this.fives = fives;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "WithdrawResponse{" +
                "fifties=" + fifties +
                ", twenties=" + twenties +
                ", tens=" + tens +
                ", fives=" + fives +
                '}';
    }
}
