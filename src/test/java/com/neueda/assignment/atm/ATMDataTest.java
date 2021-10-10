package com.neueda.assignment.atm;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ATMDataTest {


    @Test
    @DisplayName("Should return correct sum for given money")
    public void shouldReturnTwothousandsForGivenMoney() {
        Money money1 = new Money(10, MoneyValue.FIFTY);
        Money money2 = new Money(40, MoneyValue.TWENTY);
        Money money3 = new Money(50, MoneyValue.TEN);
        Money money4 = new Money(40, MoneyValue.FIVE);
        Money[] moniesToATM = {money1, money2, money3, money4};
        ATMData atmData = new ATMData(moniesToATM);
        double sumCash = atmData.sumCash();
        assertThat(sumCash).isEqualTo(2000);
    }

    @Test
    @DisplayName("Should return correct amount of notes for given value")
    public void shouldReturnTwoFiftiesOneTwentyAndOneFive() {
        Money money1 = new Money(10, MoneyValue.FIFTY);
        Money money2 = new Money(40, MoneyValue.TWENTY);
        Money money3 = new Money(50, MoneyValue.TEN);
        Money money4 = new Money(40, MoneyValue.FIVE);
        Money[] moniesToATM = {money1, money2, money3, money4};
        ATMData atmData = new ATMData(moniesToATM);
        WithdrawalResponse calc = atmData.convertMoneyIntoValues(135, moniesToATM);
        int quantityOfFifty = calc.getWithdrawal().stream().filter(e -> e.getMoneyValues().equals(MoneyValue.FIFTY)).findFirst().get().getQuantity();
        int quantityOfTwenty = calc.getWithdrawal().stream().filter(e -> e.getMoneyValues().equals(MoneyValue.TWENTY)).findFirst().get().getQuantity();
        int quantityOfTen = calc.getWithdrawal().stream().filter(e -> e.getMoneyValues().equals(MoneyValue.TEN)).findFirst().get().getQuantity();
        int quantityOfFive = calc.getWithdrawal().stream().filter(e -> e.getMoneyValues().equals(MoneyValue.FIVE)).findFirst().get().getQuantity();
        assertThat(quantityOfFifty).isEqualTo(2);
        assertThat(quantityOfTwenty).isEqualTo(1);
        assertThat(quantityOfTen).isEqualTo(1);
        assertThat(quantityOfFive).isEqualTo(1);
    }

    @Test
    @DisplayName("Should throw exception when number is not devided by 5")
    public void shouldThrowExceptionWhenNumberIsNotDevidedByFive() {
        Money money1 = new Money(10, MoneyValue.FIFTY);
        Money money2 = new Money(40, MoneyValue.TWENTY);
        Money money3 = new Money(50, MoneyValue.TEN);
        Money money4 = new Money(40, MoneyValue.FIVE);
        Money[] moniesToATM = {money1, money2, money3, money4};
        ATMData atmData = new ATMData(moniesToATM);
        atmData.convertMoneyIntoValues(127, moniesToATM);
    }


}
