package com.neueda.assignment;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ATMService implements UserRepository {

    private List<User> users;

    public ATMService() {
        this.users = new ArrayList<>();
        users.add(new User("1234", "1234", 123, 1223));
//        users.add(new User("1234", "1234", 123, 1223));
//        users.add(new User("1234", "1234", 123, 1223));
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    public double checkBalance(String accountNumber, String pin) throws WrongPinException, UserNotExist {
        User userToCheck = null;
        for (User u : users) {
            if (u.getAccountNumber().equals(accountNumber)) {
                System.out.println(accountNumber);
                userToCheck = u;
            } else {
                throw new UserNotExist("User with this id not exists in database!");
            }
        }
        if (userToCheck.getPin().equals(pin)) {
            System.out.println(userToCheck.getPin());
            System.out.println(pin);
            return userToCheck.getBalance();
        } else {
            throw new WrongPinException("Wrong PIN!");
        }
    }

}
