package com.neueda.assignment.atm;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ATMDataTest {

    @Test
    @DisplayName("Should return correct sum for given money")
    public void shouldReturnTwothousandsForGivenMoney(){
        Money money1 = new Money(10, MoneyValues.FIFTY);
        Money money2 = new Money(40, MoneyValues.TWENTY);
        Money money3 = new Money(50, MoneyValues.TEN);
        Money money4 = new Money(40, MoneyValues.FIVE);
        Money[] moniesToATM = {money1, money2, money3, money4};
        ATMUtils atmUtils = new ATMUtils();

        double sumCashInAtm = atmUtils.sumCashInAtm(moniesToATM);
        assertThat(sumCashInAtm).isEqualTo(2000);
    }

    @Test
    @DisplayName("Should return correct amount of notes for given value")
    public void shouldReturnTwoFiftiesOneTwentyAndOneFive(){
        Money money1 = new Money(10, MoneyValues.FIFTY);
        Money money2 = new Money(40, MoneyValues.TWENTY);
        Money money3 = new Money(50, MoneyValues.TEN);
        Money money4 = new Money(40, MoneyValues.FIVE);
        Money[] moniesToATM = {money1, money2, money3, money4};
        ATMData atmData = new ATMData();
        WithdrawalResponse calc = atmData.calc(125);
        assertThat(calc.getFifties()).isEqualTo(2);
        assertThat(calc.getTwenties()).isEqualTo(1);
        assertThat(calc.getFives()).isEqualTo(1);
    }




}
