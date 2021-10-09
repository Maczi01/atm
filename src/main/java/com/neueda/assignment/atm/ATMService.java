package com.neueda.assignment.atm;

import com.neueda.assignment.card.Card;
import com.neueda.assignment.card.CardDTO;
import com.neueda.assignment.card.CardMapper;
import com.neueda.assignment.card.CardRepository;
import com.neueda.assignment.exceptions.UserNotExistException;
import com.neueda.assignment.exceptions.WrongAmountException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;
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

    public double checkBalance(CardDTO cardDTO) throws UserNotExistException {
        Optional<Card> card = cardRepository.readCardByAccountNumber(cardDTO.getAccountNumber());
        if(card.isEmpty()){
            throw new UserNotExistException("User not exists");
        }
        log.info("Current balance is: ", card.get().getBalance());
        return card.get().getBalance();
        //        List<Card> cardList = cardRepository.findAll();
//        Optional<CardDTO> first = cardList.stream()
//                .map(cardMapper::mapToDTO)
//                .filter(card -> Objects.equals(card.getAccountNumber(), cardDTO.getAccountNumber()))
//                .findFirst();
//        if (first.isEmpty()) {
//            throw new UserNotExistException("User not exists!");
//        }
//        return first.get().getBalance();
    }

    public double makeWithdrawal(WithdrawalRequest withdrawalRequest) throws WrongAmountException, UserNotExistException {
        if (withdrawalRequest.getAmount() % 5 != 0) {
            log.error("Amount not devided by 5");
            throw new WrongAmountException("Incorrect amount!");
        }
        Optional<Card> card = cardRepository.readCardByAccountNumber(withdrawalRequest.getAccountNumber());
        if(card.isEmpty()){
            log.error("User not exists");
            throw new UserNotExistException("User not exists");
        }

        return card.get().getBalance();
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
