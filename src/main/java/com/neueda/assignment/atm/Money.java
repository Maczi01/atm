package com.neueda.assignment.atm;

public class Money {

    private int quantity;
    private MoneyValues moneyValues;

    public Money(int quantity, MoneyValues moneyValues) {
        this.quantity = quantity;
        this.moneyValues = moneyValues;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public MoneyValues getMoneyValues() {
        return moneyValues;
    }

    public void setMoneyValues(MoneyValues moneyValues) {
        this.moneyValues = moneyValues;
    }
}
