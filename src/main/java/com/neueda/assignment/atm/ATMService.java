package com.neueda.assignment.atm;

import com.neueda.assignment.card.Card;
import com.neueda.assignment.card.CardDTO;
import com.neueda.assignment.card.CardMapper;
import com.neueda.assignment.card.CardRepository;
import com.neueda.assignment.exceptions.NotEnoughMoneyInATMException;
import com.neueda.assignment.exceptions.NotEnoughMoneyOnAccountException;
import com.neueda.assignment.exceptions.UserNotExistException;
import com.neueda.assignment.exceptions.WrongAmountException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ATMService {

    Money money1 = new Money(10, MoneyValues.FIFTY);
    Money money2 = new Money(30, MoneyValues.TWENTY);
    Money money3 = new Money(30, MoneyValues.TEN);
    Money money4 = new Money(2, MoneyValues.FIFTY);
    Money [] monies = {money1,money2,money3,money4};


    private CardRepository cardRepository;
    private CardMapper cardMapper;
    private ATMData atmData = new ATMData(monies);

    public ATMService(CardRepository cardRepository, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
    }

    public double cashInATM() {
//        return atmData.getCash();
        return 0;
    }

    public double checkBalance(CardDTO cardDTO) throws UserNotExistException, WrongPinException {
        Card card = cardRepository.findByAccountNumber(cardDTO.getAccountNumber())
                .orElseThrow(() -> new UserNotExistException("User not exists"));
        if (!card.getPin().equals(cardDTO.getPin())) {
            throw new WrongPinException("Wrong PIN, try again!");
        }
        log.info("Current balance is: {}", card.getBalance());
        return card.getBalance();
    }

    public WithdrawalResponse makeWithdrawal(WithdrawalRequest withdrawalRequest) throws WrongAmountException, UserNotExistException, NotEnoughMoneyInATMException, NotEnoughMoneyOnAccountException, WrongPinException {
        double amount = withdrawalRequest.getAmount();
        if (amount % 5 != 0) {
            log.error("Amount not devided by 5");
            throw new WrongAmountException("Incorrect amount!");
        }
//        if (amount > atmData.getCash()) {
        if (amount > 10000) {
            log.error("Amount not devided by 5");
            throw new NotEnoughMoneyInATMException("Incorrect amount!");
        }
        Card card = cardRepository.findByAccountNumber(withdrawalRequest.getAccountNumber())
                .orElseThrow(() -> new UserNotExistException("User not exists"));
        if (!card.getPin().equals(withdrawalRequest.getPin())) {
            throw new WrongPinException("Wrong PIN, try again!");
        }
        double currentBalance = card.getBalance();
        double overdraft = card.getOverdraft();
        if (amount > currentBalance + overdraft) {
            throw new NotEnoughMoneyOnAccountException("Not enough money on your account");
        }
        log.info("Cash on account before withdrawal {}", currentBalance);
//        atmData.setCash(atmData.getCash() - amount);
        card.setBalance(currentBalance - amount);
        cardRepository.save(card);
        log.info("Cash on account after withdrawal {}", currentBalance);
        return calc(amount);
    }

    private WithdrawalResponse calc(double moneyToWithdrawal) {
        WithdrawalResponse withdrawResponse = new WithdrawalResponse();
        int moneyValue = (int) moneyToWithdrawal;
        Integer[] noteValues = {50, 20, 10, 5};
        for (int i = 0; i < noteValues.length && moneyValue != 0; i++) {

            Integer noteValue = noteValues[i];
                switch (noteValue) {
                    case 50:
                        withdrawResponse.setFifties(moneyValue / noteValue);
                        break;
                    case 20:
                        withdrawResponse.setTwenties(moneyValue / noteValue);
                        break;
                    case 10:
                        withdrawResponse.setTens(moneyValue / noteValue);
                        break;
                    case 5:
                        withdrawResponse.setFives(moneyValue / noteValue);
                        break;
                }
                System.out.println("No of " + noteValue + "'s" + " :" + moneyValue / noteValue);
                moneyValue = moneyValue % noteValue;
        }
        return withdrawResponse;
    }

}
