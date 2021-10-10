package com.neueda.assignment.atm;

public class ATMData {

    private Money[] monies;
    private double cash;

    Money money1 = new Money(10, MoneyValues.FIFTY);
    Money money2 = new Money(30, MoneyValues.TWENTY);
    Money money3 = new Money(30, MoneyValues.TEN);
    Money money4 = new Money(20, MoneyValues.FIVE);
    Money[] moniesToATM = {money1, money2, money3, money4};

    public ATMData() {
        this.monies = moniesToATM;
        this.cash = sumCashInATM();
    }

    public Money[] getMonies() {
        return monies;
    }

    public void setMonies(Money[] monies) {
        this.monies = monies;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double sumCashInATM() {
        double sum = 0;
        for (Money money : monies) {
            sum += money.getMoneyValues().getValue() * money.getQuantity();
        }
        valuesInAtm();
        return sum;
    }

    public void valuesInAtm(){
        for (Money money : monies) {
            System.out.println(money.getMoneyValues() +": "+ money.getQuantity());
        }
    }

    public int[] moneyValues() {
        int[] values = new int[monies.length];
        for (int i = 0; i < monies.length; i++) {
            values[i] = monies[i].getMoneyValues().getValue();
        }
        return values;
    }

    public WithdrawalResponse calc(double moneyToWithdrawal) {
        WithdrawalResponse withdrawResponse = new WithdrawalResponse();
        int moneyValue = (int) moneyToWithdrawal;
        int[] noteValues = moneyValues();
        for (int i = 0; i < noteValues.length && moneyValue != 0; i++) {

            Integer noteValue = noteValues[i];
            switch (noteValue) {
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
            System.out.println("No of " + noteValue + "'s" + " :" + moneyValue / noteValue);
            moneyValue = moneyValue % noteValue;
        }
        return withdrawResponse;
    }

}
