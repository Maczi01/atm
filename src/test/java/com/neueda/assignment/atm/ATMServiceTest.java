package com.neueda.assignment.atm;

import com.neueda.assignment.card.Card;
import com.neueda.assignment.card.CardRepository;
import com.neueda.assignment.exceptions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;


@SpringBootTest
public class ATMServiceTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private ATMService atmService = new ATMService(cardRepository);

    private ATMData atmData = new ATMData(bootstrapTestData());

    private Money[] bootstrapTestData() {
        Money money1 = new Money(10, MoneyValue.FIFTY);
        Money money2 = new Money(30, MoneyValue.TWENTY);
        Money money3 = new Money(30, MoneyValue.TEN);
        Money money4 = new Money(20, MoneyValue.FIVE);
        Money[] moniesToATM = {money1, money2, money3, money4};
        return moniesToATM;
    }

    @Test
    @DisplayName("Should return correct sum for choosen card")
    public void shouldReturnCorrectBalanceAndMaximumAmountToWithdrawal() throws CardNotExistException, WrongPinException {
//        given
        CheckBalanceRequest checkBalanceRequest = new CheckBalanceRequest("123", "1234");
        Optional<Card> card = Optional.of(new Card(1L, "123", "1234", 2000, 100));
//        when
        when(cardRepository.findByAccountNumber(anyString())).thenReturn(card);
//        then
        CheckBalanceResponse checkBalanceResponse = atmService.checkBalance(checkBalanceRequest);
        assertThat(checkBalanceResponse.getCurrentBalance()).isEqualTo(2000);
        assertThat(checkBalanceResponse.getMaximumAmountToWithdrawal()).isEqualTo(2100);
    }

    @Test
    @DisplayName("Cannot check balance and should throw exception when Pin is incorrect")
    public void shouldThrowExceptionWhenPinIsIncorrect() {
//        given
        CheckBalanceRequest checkBalanceRequest = new CheckBalanceRequest("123", "1234");
//        when
        Optional<Card> card = Optional.of(new Card(1L, "123", "1347", 2000, 100));
        when(cardRepository.findByAccountNumber(anyString())).thenReturn(card);
//        then
        assertThatThrownBy(() -> {
            atmService.checkBalance(checkBalanceRequest);
        }).isInstanceOf(WrongPinException.class)
                .hasMessage("Wrong PIN, try again!");
    }

    @Test
    @DisplayName("Cannot make withdraw and should throw exception when number is not devided by 5")
    public void shouldThrowExceptionWhenNumberIsNotDevidedByFive() throws CardNotExistException, WrongPinException {
//        given
        WithdrawalRequest withdrawalRequest = new WithdrawalRequest(333, "123", "1347");
//        when
        Optional<Card> card = Optional.of(new Card(1L, "123", "1347", 2000, 100));
        when(cardRepository.findByAccountNumber(anyString())).thenReturn(card);
//        then
        assertThatThrownBy(() -> {
            atmService.makeWithdrawal(withdrawalRequest);
        }).isInstanceOf(WrongAmountException.class)
                .hasMessage("Incorrect amount!");
    }

    @Test
    @DisplayName("Cannot make withdraw and should throw exception when is not enough money ATM")
    public void shouldThrowExceptionWhenWithdrawalIsbiggerThanAmountMoneyInATM() {
//        given
        WithdrawalRequest withdrawalRequest = new WithdrawalRequest(3330, "123", "1347");
//        when
        Optional<Card> card = Optional.of(new Card(1L, "123", "1347", 2000, 100));
        when(cardRepository.findByAccountNumber(anyString())).thenReturn(card);
//        then
        assertThatThrownBy(() -> {
            atmService.makeWithdrawal(withdrawalRequest);
        }).isInstanceOf(NotEnoughMoneyInATMException.class)
                .hasMessage("Not enough money in ATM!");
    }


    @Test
    @DisplayName("Should throw exception when card not exists")
    public void shouldThrowExceptionWhenCardWithGivenNumberNotExists() {
//        given
        WithdrawalRequest withdrawalRequest = new WithdrawalRequest(330, "997", "1347");
//        when
        Optional<Card> card = Optional.of(new Card(1L, "123", "1347", 2000, 100));
        when(cardRepository.findByAccountNumber(anyString())).thenReturn(card);
//        then
        assertThat(catchThrowable(() -> {
            throw new CardNotExistException("Card not exists");
        })).as("Card not exists")
                .isInstanceOf(CardNotExistException.class)
                .hasMessageContaining("Card not exists");
    }

    @Test
    @DisplayName("Should throw exception when balance is smaller than withdrawal")
    public void shouldThrowExceptionWhenBalanceIsSmallerThanWithdrawal() {
//        given
        WithdrawalRequest withdrawalRequest = new WithdrawalRequest(350, "123", "1347");
//        when
        Optional<Card> card = Optional.of(new Card(1L, "123", "1347", 200, 100));
        when(cardRepository.findByAccountNumber(anyString())).thenReturn(card);
//        then
        assertThatThrownBy(() -> {
            atmService.makeWithdrawal(withdrawalRequest);
        }).isInstanceOf(NotEnoughMoneyOnAccountException.class)
                .hasMessage("Not enough money on your account");
    }


}
