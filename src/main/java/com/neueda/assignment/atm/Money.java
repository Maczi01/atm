package com.neueda.assignment.atm;

public class Money {

    private int quantity;
    private MoneyValue moneyValue;

    public Money(int quantity, MoneyValue moneyValue) {
        this.quantity = quantity;
        this.moneyValue = moneyValue;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public MoneyValue getMoneyValues() {
        return moneyValue;
    }

    public void setMoneyValues(MoneyValue moneyValue) {
        this.moneyValue = moneyValue;
    }
}
