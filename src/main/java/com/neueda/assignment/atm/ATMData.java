package com.neueda.assignment.atm;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ATMData {

    private Money[] monies;

    public ATMData(Money[] monies) {
        this.monies = monies;
    }

    public Money[] getMonies() {
        return monies;
    }

    public void setMonies(Money[] monies) {
        this.monies = monies;
    }

    public double sumCash() {
        double sum = 0;
        for (Money money : monies) {
            sum += money.getMoneyValues().getValue() * money.getQuantity();
        }
        valuesInATMlogger(monies);
        return sum;
    }

    private void valuesInATMlogger(Money[] monies) {
        for (Money money : monies) {
            log.info(money.getMoneyValues() + ": " + money.getQuantity());
        }
    }

    public WithdrawalResponse convertMoneyIntoValues(double moneyToWithdrawal, Money[] monies) {
        WithdrawalResponse withdrawResponse = new WithdrawalResponse();
        List<Money> list = new ArrayList<>();
        int moneyValue = (int) moneyToWithdrawal;
        List<Integer> collecttionOfNotes = Arrays.stream(monies).map(e -> e.getMoneyValues().getValue()).collect(Collectors.toList());

        for (int i = 0; i < collecttionOfNotes.size(); i++) {
            if (moneyValue >= collecttionOfNotes.get(i) && collecttionOfNotes.get(i) > 0) {
                list.add(new Money(moneyValue / collecttionOfNotes.get(i), MoneyValue.values()[i]));
                monies[i].setQuantity(monies[i].getQuantity() - moneyValue / collecttionOfNotes.get(i));
            }
            moneyValue = moneyValue % collecttionOfNotes.get(i);
        }
        for (Money money : list) {
            System.out.println(money.getMoneyValues().getValue() + " " + money.getQuantity());
        }
        valuesInATMlogger(monies);
        withdrawResponse.setWithdrawal(list);
        return withdrawResponse;
    }

}
