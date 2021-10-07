package com.neueda.assignment;

import java.util.Objects;

public class AutomaterTellerMachine {

    private double cash;

    public AutomaterTellerMachine(double cash) {
        this.cash = cash;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutomaterTellerMachine that = (AutomaterTellerMachine) o;
        return Double.compare(that.cash, cash) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cash);
    }

    @Override
    public String toString() {
        return "AutomaterTellerMachine{" +
                "cash=" + cash +
                '}';
    }
}
