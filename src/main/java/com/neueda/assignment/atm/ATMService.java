package com.neueda.assignment.atm;

import com.neueda.assignment.card.Card;
import com.neueda.assignment.card.CardDTO;
import com.neueda.assignment.card.CardMapper;
import com.neueda.assignment.card.CardRepository;
import com.neueda.assignment.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ATMService {

    private CardRepository cardRepository;
    private CardMapper cardMapper;
    private ATMData atmData = new ATMData();

    public ATMService(CardRepository cardRepository, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
    }

    public double checkCashInATM() {
        return atmData.sumCashInATM();
    }

    public CheckBalanceResponse checkBalance(CardDTO cardDTO) throws UserNotExistException, WrongPinException {
        Card card = cardRepository.findByAccountNumber(cardDTO.getAccountNumber())
                .orElseThrow(() -> new UserNotExistException("User not exists"));
        if (!card.getPin().equals(cardDTO.getPin())) {
            throw new WrongPinException("Wrong PIN, try again!");
        }
        log.info("Current balance is: {}", card.getBalance());
        log.info("Maximum amount to withdrawal is: {}", card.getBalance() + card.getOverdraft());
        CheckBalanceResponse checkBalanceResponse = new CheckBalanceResponse();
        checkBalanceResponse.setCurrentBalance(card.getBalance());
        checkBalanceResponse.setMaximumAmountToWithdrawal(card.getBalance() + card.getOverdraft());
        return checkBalanceResponse;
    }

    public WithdrawalResponse makeWithdrawal(WithdrawalRequest withdrawalRequest) throws WrongAmountException, UserNotExistException, NotEnoughMoneyInATMException, NotEnoughMoneyOnAccountException, WrongPinException {
        double amount = withdrawalRequest.getAmount();
        if (amount % 5 != 0) {
            log.error("Amount not devided by 5");
            throw new WrongAmountException("Incorrect amount!");
        }
        if (amount > atmData.sumCashInATM()) {
            log.error("Not enough money in ATM!");
            throw new NotEnoughMoneyInATMException("Not enough money in ATM!");
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
        atmData.setCash(atmData.getCash() - amount);
        card.setBalance(currentBalance - amount);
        cardRepository.save(card);
        log.info("Cash on account after withdrawal {}", currentBalance);
        WithdrawalResponse calc = atmData.calc(amount);
        calc.setBalance(currentBalance - amount);
        return calc;
    }

}
