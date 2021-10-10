package com.neueda.assignment.atm;

public enum MoneyValue {

    FIFTY(50), TWENTY(20), TEN(10), FIVE(5);

    private int moneyValue;

    MoneyValue(int moneyValue) {
        this.moneyValue = moneyValue;
    }

    public int getValue() {
        return moneyValue;
    }

}
