package com.neueda.assignment.atm;

public class ATMData {

    private Money [] monies;

    public ATMData(Money[] monies) {
        this.monies = monies;
    }

    public Money[] getMonies() {
        return monies;
    }

    public void setMonies(Money[] monies) {
        this.monies = monies;
    }
}
