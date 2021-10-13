package com.neueda.assignment.atm;

import com.neueda.assignment.card.Card;
import com.neueda.assignment.card.CardRepository;
import com.neueda.assignment.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
public class ATMService {

    private CardRepository cardRepository;
    private ATMData atmData = new ATMData(bootstrapTestData());

    public ATMService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public double checkCashInATM() {
        return atmData.sumCash();
    }

    public CheckBalanceResponse checkBalance(CheckBalanceRequest checkBalanceRequest) throws CardNotExistException, WrongPinException {
        Card card = cardRepository.findByAccountNumber(checkBalanceRequest.getAccountNumber())
                .orElseThrow(() -> new CardNotExistException("User not exists"));
        if (!card.getPin().equals(checkBalanceRequest.getPin())) {
            throw new WrongPinException("Wrong PIN, try again!");
        }
        log.info("Current balance is: {}", card.getBalance());
        log.info("Maximum amount to withdrawal is: {}", card.getBalance() + card.getOverdraft());
        return new CheckBalanceResponse(card.getBalance(), card.maximumAmountToWithdraw());
    }

    @Transactional
    public WithdrawalResponse makeWithdrawal(WithdrawalRequest withdrawalRequest) throws WrongAmountException, CardNotExistException, NotEnoughMoneyInATMException, NotEnoughMoneyOnAccountException, WrongPinException {
        double amount = withdrawalRequest.getAmount();
        if (amount % 5 != 0) {
            log.error("Amount not devided by 5");
            throw new WrongAmountException("Incorrect amount!");
        }
        if (amount > atmData.sumCash()) {
            log.error("Not enough money in ATM!");
            throw new NotEnoughMoneyInATMException("Not enough money in ATM!");
        }
        Card card = cardRepository.findByAccountNumber(withdrawalRequest.getAccountNumber())
                .orElseThrow(() -> new CardNotExistException("Card not exists"));
        if (!card.getPin().equals(withdrawalRequest.getPin())) {
            throw new WrongPinException("Wrong PIN, try again!");
        }
        if (amount > card.maximumAmountToWithdraw()) {
            throw new NotEnoughMoneyOnAccountException("Not enough money on your account");
        }
        log.info("Cash on account before withdrawal {}", card.getBalance());
        double balanceAfterWithdrawal = card.calculateBalanceAfterWithdraw(amount);
        card.setBalance(balanceAfterWithdrawal);
        log.info("Cash on account after withdrawal {}", card.getBalance());
        WithdrawalResponse withdrawalResponse = atmData.convertMoneyIntoValues(amount);
        withdrawalResponse.setBalance(balanceAfterWithdrawal);
        return withdrawalResponse;
    }

    private Money[] bootstrapTestData() {
        Money money1 = new Money(10, MoneyValue.FIFTY);
        Money money2 = new Money(30, MoneyValue.TWENTY);
        Money money3 = new Money(30, MoneyValue.TEN);
        Money money4 = new Money(20, MoneyValue.FIVE);
        return new Money[]{money1, money2, money3, money4};
    }

}
