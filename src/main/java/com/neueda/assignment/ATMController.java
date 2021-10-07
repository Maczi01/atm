package com.neueda.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
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


}
