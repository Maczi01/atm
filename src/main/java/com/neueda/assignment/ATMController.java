package com.neueda.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ATMController {

    private ATMService atmService;

    @Autowired
    public ATMController(ATMService atmService) {
        this.atmService = atmService;
    }

    @GetMapping()
    public List<User> getAllUsers(){
        return atmService.findAll();
    }

    @GetMapping("/getBalance")
    public double checkBalance() throws WrongPinException, UserNotExist {
        return atmService.checkBalance();
    }
//
//    @PostMapping(/)
//    public double checkBalance(@RequestParam String accountNumber,@RequestBody String pin) throws WrongPinException, UserNotExist {
//        return atmService.checkBalance(accountNumber, pin);
//    }

}
