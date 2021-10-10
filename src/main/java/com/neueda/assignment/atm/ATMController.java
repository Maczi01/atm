package com.neueda.assignment.atm;

import com.neueda.assignment.card.CardDTO;
import com.neueda.assignment.exceptions.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class ATMController {

    private ATMService atmService;

    public ATMController(ATMService atmService) {
        this.atmService = atmService;
    }

    @GetMapping("/cash")
    public double cashInATM() {
        return atmService.checkCashInATM();
    }

    @PostMapping("/getBalance")
    public CheckBalanceResponse checkBalance(@RequestBody CardDTO cardDTO) throws UserNotExistException, WrongPinException {
        return atmService.checkBalance(cardDTO);
    }

    @PostMapping("/makeWithdrawal")
    public WithdrawalResponse makeWithdrawal(@RequestBody WithdrawalRequest withdrawalRequest) throws WrongAmountException, NotEnoughMoneyOnAccountException, NotEnoughMoneyInATMException, UserNotExistException, WrongPinException {
        return atmService.makeWithdrawal(withdrawalRequest);
    }

}
