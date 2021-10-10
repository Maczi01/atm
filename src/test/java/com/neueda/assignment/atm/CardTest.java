package com.neueda.assignment.atm;

import com.neueda.assignment.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

    private Card card;

    @Test
    @DisplayName("Calculate correct sume to withdraw - balance plus overdraft")
    public void shouldReturnTwoThousandFiveHundredPossibletoWithdraw(){
//        given
        card = new Card(1L, "123", "1234", 2000, 500);
//        when
        double maximumAmountToWithdraw = card.maximumAmountToWithdraw();
//        then
        assertThat(maximumAmountToWithdraw).isEqualTo(2500);
    }

    @Test
    @DisplayName("Calculate correct sume to withdraw - balance plus overdraft")
    public void shouldReturnOneThousandTwoHundredPossibletoWithdraw(){
//        given
        card = new Card(1L, "123", "1234", 1000, 200);
//        when
        double maximumAmountToWithdraw = card.maximumAmountToWithdraw();
//        then
        assertThat(maximumAmountToWithdraw).isEqualTo(1200);
    }

    @Test
    @DisplayName("Calculate balance after withdraw")
    public void shouldReturnOneThousandNineHundredAfterWithdraw(){
//        given
        card = new Card(1L, "123", "1234", 2000, 500);
//        when
        double maximumAmountToWithdraw = card.calculateBalanceAfterWithdraw(100);
//        then
        assertThat(maximumAmountToWithdraw).isEqualTo(1900);
    }
}
