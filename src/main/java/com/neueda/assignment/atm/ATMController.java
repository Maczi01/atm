package com.neueda.assignment.atm;

import com.neueda.assignment.card.Card;
import com.neueda.assignment.card.CardDTO;
import com.neueda.assignment.card.CardMapper;
import com.neueda.assignment.card.CardRepository;
import com.neueda.assignment.exceptions.NotEnoughMoneyInATMException;
import com.neueda.assignment.exceptions.NotEnoughMoneyOnAccountException;
import com.neueda.assignment.exceptions.UserNotExistException;
import com.neueda.assignment.exceptions.WrongAmountException;
import com.neueda.assignment.atm.ATMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ATMController {

    private ATMService atmService;

    public ATMController(ATMService atmService) {
        this.atmService = atmService;
    }

    @GetMapping("/cash")
    public double cashInATM() {
        return atmService.cashInATM();
    }

    @PostMapping("/getBalance")
    public double checkBalance(@RequestBody CardDTO cardDTO) throws UserNotExistException {
        return atmService.checkBalance(cardDTO);
    }

    @PostMapping("/makeWithdrawal")
    public double makeWithdrawal(@RequestBody WithdrawalRequest withdrawalRequest) throws WrongAmountException, NotEnoughMoneyOnAccountException, NotEnoughMoneyInATMException, UserNotExistException {
        return atmService.makeWithdrawal(withdrawalRequest);
    }

}
