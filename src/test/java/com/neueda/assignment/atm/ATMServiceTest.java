package com.neueda.assignment.atm;

import com.neueda.assignment.card.Card;
import com.neueda.assignment.card.CardMapper;
import com.neueda.assignment.card.CardRepository;
import com.neueda.assignment.exceptions.UserNotExistException;
import com.neueda.assignment.exceptions.WrongPinException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import javax.persistence.Access;
import java.util.List;
import java.util.Optional;


//@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ATMServiceTest {

    @Mock
    CardRepository cardRepository;
    private ATMData atmData = new ATMData(bootstrapTestData());

    @InjectMocks
    ATMService atmService = new ATMService(cardRepository);


    @Test
    public void checkBalanceTest() throws UserNotExistException, WrongPinException {
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
    public void shouldThrowExceptionWhenPinIsIncorrect() throws UserNotExistException, WrongPinException {
//        given
        CheckBalanceRequest checkBalanceRequest = new CheckBalanceRequest("123", "1234");
//        when
        Optional<Card> card = Optional.of(new Card(1L, "123", "1347", 2000, 100));
        when(cardRepository.findByAccountNumber(anyString())).thenReturn(card);
//        then
        assertThatThrownBy(() -> {
            atmService.checkBalance(checkBalanceRequest);
        }).isInstanceOf(WrongPinException.class);

    }

//TODO kontekst sprigna tylko dla beanów, bez bazy;

    private Money[] bootstrapTestData() {
        Money money1 = new Money(10, MoneyValue.FIFTY);
        Money money2 = new Money(30, MoneyValue.TWENTY);
        Money money3 = new Money(30, MoneyValue.TEN);
        Money money4 = new Money(20, MoneyValue.FIVE);
        Money[] moniesToATM = {money1, money2, money3, money4};
        return moniesToATM;
    }

}
