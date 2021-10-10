package com.neueda.assignment.atm;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

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


//    public double sumCashInATM() {
//        return atmUtilssumCashInAtm(monies);
//    }

    public int[] moneyValues() {
        int[] values = new int[monies.length];
        for (int i = 0; i < monies.length; i++) {
            values[i] = monies[i].getMoneyValues().getValue();
        }
        return values;
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

    //    TODO change name
    public WithdrawalResponse calc(double moneyToWithdrawal) {
        WithdrawalResponse withdrawResponse = new WithdrawalResponse();
        List<Double> list = new ArrayList<>();
        int moneyValue = (int) moneyToWithdrawal;
        int[] noteValues = moneyValues();
        for (int i = 0; i < noteValues.length && moneyValue != 0; i++) {
            Integer noteValue = noteValues[i];
            switch (noteValue) {
                case noteValue:

                case 50:
                    withdrawResponse.setFifties(moneyValue / noteValue);
                    money1.setQuantity(money1.getQuantity() - moneyValue / noteValue);
                    break;
                case 20:
                    withdrawResponse.setTwenties(moneyValue / noteValue);
                    money2.setQuantity(money2.getQuantity() - moneyValue / noteValue);
                    break;
                case 10:
                    withdrawResponse.setTens(moneyValue / noteValue);
                    money3.setQuantity(money3.getQuantity() - moneyValue / noteValue);
                    break;
                case 5:
                    withdrawResponse.setFives(moneyValue / noteValue);
                    money4.setQuantity(money4.getQuantity() - moneyValue / noteValue);
                    break;
            }
            log.info("No of " + noteValue + "'s" + " :" + moneyValue / noteValue);
            moneyValue = moneyValue % noteValue;
        }
        return withdrawResponse;
    }

}
