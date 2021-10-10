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

import java.util.Optional;

@Slf4j
@Service
public class ATMService {

    private CardRepository cardRepository;
    private CardMapper cardMapper;
    private ATMData atmData = new ATMData(1500);

    public ATMService(CardRepository cardRepository, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
    }

    public double cashInATM() {
        return atmData.getCash();
    }

    public double checkBalance(CardDTO cardDTO) throws UserNotExistException, WrongPinException {
        Optional<Card> card = cardRepository.findCardByAccountNumber(cardDTO.getAccountNumber());
        if (card.isEmpty()) {
            throw new UserNotExistException("User not exists");
        }
        if (!card.get().getPin().equals(cardDTO.getPin())) {
            throw new WrongPinException("Wrong PIN, try again!");
        }
        log.info("Current balance is: {}", card.get().getBalance());
        return card.get().getBalance();
    }

    public double makeWithdrawal(WithdrawalRequest withdrawalRequest) throws WrongAmountException, UserNotExistException, NotEnoughMoneyInATMException, NotEnoughMoneyOnAccountException, WrongPinException {
        double amount = withdrawalRequest.getAmount();
        if (amount % 5 != 0) {
            log.error("Amount not devided by 5");
            throw new WrongAmountException("Incorrect amount!");
        }
        if (amount > atmData.getCash()) {
            log.error("Amount not devided by 5");
            throw new NotEnoughMoneyInATMException("Incorrect amount!");
        }
        Optional<Card> card = cardRepository.findCardByAccountNumber(withdrawalRequest.getAccountNumber());
        if (card.isEmpty()) {
            log.error("User not exists");
            throw new UserNotExistException("User not exists");
        }
        if (!card.get().getPin().equals(withdrawalRequest.getPin())) {
            throw new WrongPinException("Wrong PIN, try again!");
        }
        double currentBalance = card.get().getBalance();
        double overdraft = card.get().getOverdraft();
        if (amount > currentBalance + overdraft) {
            throw new NotEnoughMoneyOnAccountException("Not enough money on your account");
        }
        log.info("Cash on account before withdrawal {}", currentBalance);
        atmData.setCash(atmData.getCash() - amount);
        card.get().setBalance(currentBalance - amount);
        cardRepository.save(card.get());
        log.info("Cash on account after withdrawal {}", currentBalance);
        return amount;
    }


    //
//    public double checkBalance() throws UserNotExistException {
//        String username = getUsername();
//
//        for (Card card : this.card) {
//            if (card.getAccountNumber().equals(username)) {
//                return card.getBalance();
//            }
//        }
//
//        throw new UserNotExistException("User not found");
//    }
//
//    public double makeWithdrawal(double withdrawalAmount) throws WrongAmountException, NotEnoughMoneyOnAccountException, NotEnoughMoneyInATMException {
//        String username = getUsername();
//
//        for (Card card : this.card) {
//            if (card.getAccountNumber().equals(username)) {
//                if (withdrawalAmount > card.getBalance()) {
//                    throw new NotEnoughMoneyOnAccountException("You haven't got enough money to withdraw " + withdrawalAmount);
//                }
//                if (withdrawalAmount > automaterTellerMachine.getCash()) {
//                    throw new NotEnoughMoneyInATMException("Not enough money in machine!");
//                }
//                card.setBalance(card.getBalance() - withdrawalAmount);
//                System.out.println("Account balance: " + card.getBalance());
//                return withdrawalAmount;
//            }
//        }
//        return 0;
//    }
//
//    private String getUsername() {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return ((UserDetails) principal).getUsername();
//    }


}
