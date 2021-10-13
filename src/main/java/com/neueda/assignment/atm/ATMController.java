package com.neueda.assignment.atm;

import com.neueda.assignment.exceptions.*;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CheckBalanceResponse> checkBalance(@RequestBody CheckBalanceRequest checkBalanceRequest) throws CardNotExistException, WrongPinException {
        return ResponseEntity.ok(atmService.checkBalance(checkBalanceRequest));
    }

    @PostMapping("/makeWithdrawal")
    public ResponseEntity<WithdrawalResponse> makeWithdrawal(@RequestBody WithdrawalRequest withdrawalRequest) throws WrongAmountException, NotEnoughMoneyOnAccountException, NotEnoughMoneyInATMException, CardNotExistException, WrongPinException {
        return ResponseEntity.ok(atmService.makeWithdrawal(withdrawalRequest));
    }

}
