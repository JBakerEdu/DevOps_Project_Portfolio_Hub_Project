package edu.westga.comp4420.project_portfolio.model;

import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    private static List<User> accounts = new ArrayList<>();

    public static boolean createAccount(String username, String password, String email) {
        if (findUserByUsername(username) != null) {
            return false;
        }
        accounts.add(new User(username, password, email));
        return true;
    }

    public static User validateLogin(String username, String password) {
        for (User user : accounts) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private static User findUserByUsername(String username) {
        for (User user : accounts) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
