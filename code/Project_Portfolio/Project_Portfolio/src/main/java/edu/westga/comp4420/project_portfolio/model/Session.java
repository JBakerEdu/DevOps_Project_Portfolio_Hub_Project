package edu.westga.comp4420.project_portfolio.model;

public final class Session {
    private static Session instance = null;
    private User currentUser;

    private Session() {
        // private constructor (singleton)
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public void login(User user) {
        this.currentUser = user;
    }

    public void logout() {
        this.currentUser = null;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public boolean isLoggedIn() {
        return this.currentUser != null;
    }
}