package com.neueda.assignment.service;

import com.neueda.assignment.model.AutomaterTellerMachine;
import com.neueda.assignment.model.User;
import com.neueda.assignment.repository.UserRepository;
import com.neueda.assignment.exceptions.NotEnoughMoneyInATMException;
import com.neueda.assignment.exceptions.NotEnoughMoneyOnAccountException;
import com.neueda.assignment.exceptions.UserNotExistException;
import com.neueda.assignment.exceptions.WrongAmountException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ATMService implements UserRepository {

    private List<User> users;
    private AutomaterTellerMachine automaterTellerMachine;

    public ATMService(AutomaterTellerMachine automaterTellerMachine) {
        this.users = new ArrayList<>();
        users.add(new User("1234", "1234", 123, 1223));
        users.add(new User("1111", "1111", 666666666, 1223));
        this.automaterTellerMachine = automaterTellerMachine;
    }

    @Override
    public double checkBalance() throws UserNotExistException {
        String username = getUsername();

        for (User user : users) {
            if (user.getAccountNumber().equals(username)) {
                return user.getBalance();
            }
        }

        throw new UserNotExistException("User not found");
    }


    public double makeWithdrawal(double withdrawalAmount) throws WrongAmountException, NotEnoughMoneyOnAccountException, NotEnoughMoneyInATMException {
        String username = getUsername();

        if (withdrawalAmount % 5 != 0) {
            throw new WrongAmountException("Incorrect amount!");
        }

        for (User user : users) {
            if (user.getAccountNumber().equals(username)) {
                if (withdrawalAmount > user.getBalance()) {
                    throw new NotEnoughMoneyOnAccountException("You haven't got enough money to withdraw " + withdrawalAmount);
                }
                if (withdrawalAmount > automaterTellerMachine.getCash()) {
                    throw new NotEnoughMoneyInATMException("Not enough money in machine!");
                }
                user.setBalance(user.getBalance() - withdrawalAmount);
                System.out.println("Account balance: " + user.getBalance());
                return withdrawalAmount;
            }
        }
        return 0;
    }

    private String getUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetails) principal).getUsername();
    }

}
