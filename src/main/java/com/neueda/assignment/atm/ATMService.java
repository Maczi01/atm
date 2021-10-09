package com.neueda.assignment.atm;

import com.neueda.assignment.card.Card;
import com.neueda.assignment.card.CardDTO;
import com.neueda.assignment.card.CardMapper;
import com.neueda.assignment.card.CardRepository;
import com.neueda.assignment.exceptions.UserNotExistException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        System.out.println(cardDTO.getAccountNumber());
        List<Card> cardList = cardRepository.findAll();
        Optional<CardDTO> first = cardList.stream()
                .map(cardMapper::mapToDTO)
                .filter(card -> Objects.equals(card.getAccountNumber(), cardDTO.getAccountNumber()))
                .findFirst();
        if (first.isEmpty()) {
            throw new UserNotExistException("User not exists!");
        }
        return first.get().getBalance();
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
//        if (withdrawalAmount % 5 != 0) {
//            throw new WrongAmountException("Incorrect amount!");
//        }
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
