package com.neueda.assignment.atm;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class ATMUtils {
    public ATMUtils() {
    }

    public double sumCashInAtm(Money[] monies) {
        double sum = 0;
        for (Money money : monies) {
            sum += money.getMoneyValues().getValue() * money.getQuantity();
        }
        valuesInAtm(monies);
        return sum;
    }

    private void valuesInAtm(Money[] monies) {
        for (Money money : monies) {
            log.info(money.getMoneyValues() + ": " + money.getQuantity());
        }
    }
}