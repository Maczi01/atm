package com.neueda.assignment.controller;

import com.neueda.assignment.exceptions.NotEnoughMoneyInATMException;
import com.neueda.assignment.exceptions.NotEnoughMoneyOnAccountException;
import com.neueda.assignment.exceptions.UserNotExistException;
import com.neueda.assignment.exceptions.WrongAmountException;
import com.neueda.assignment.service.ATMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ATMController {

    private ATMService atmService;

    @Autowired
    public ATMController(ATMService atmService) {
        this.atmService = atmService;
    }

    @GetMapping("/getBalance")
    public double checkBalance() throws UserNotExistException {
        return atmService.checkBalance();
    }

    @PostMapping("/makeWithdrawal")
    public double makeWithdrawal(@RequestBody double amount) throws WrongAmountException, NotEnoughMoneyOnAccountException, NotEnoughMoneyInATMException {
        return atmService.makeWithdrawal(amount);
    }

}
