package com.neueda.assignment.atm;

public enum MoneyValues {

    FIFTY(50), TWENTY(20), TEN(10), FIVE(5);

    private int moneyValue;

    MoneyValues(int moneyValue) {
        this.moneyValue = moneyValue;
    }

    public int getValue() {
        return moneyValue;
    }

    //
//    int getAmount(){
//        return
//    }
}
