package com.in28minutes.login;

import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class LoginService {

    public boolean isUserValid(String user, String password) {

        Set<String> users = new HashSet<>();
        users.add("pesho");
        users.add("gosho");
        users.add("tosho");

        if (users.contains(user) && "1".equals(password)) {
            return true;
        }

        return false;
    }
}
