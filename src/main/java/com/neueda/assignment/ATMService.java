package com.neueda.assignment;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ATMService  implements UserRepository {

    private List<User> users;

    public ATMService() {
        this.users = new ArrayList<>();
        users.add(new User("1234", "1234", 123, 1223));
        users.add(new User("1234", "1234", 123, 1223));
        users.add(new User("1234", "1234", 123, 1223));
    }

    @Override
    public List<User> findAll() {
        return users;
    }
}
