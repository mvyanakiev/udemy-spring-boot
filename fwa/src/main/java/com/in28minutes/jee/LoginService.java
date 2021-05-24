package com.in28minutes.jee;

import java.util.HashSet;
import java.util.Set;

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
