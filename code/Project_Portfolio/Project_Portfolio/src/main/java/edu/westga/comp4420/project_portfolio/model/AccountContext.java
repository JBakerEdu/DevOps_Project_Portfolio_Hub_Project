package edu.westga.comp4420.project_portfolio.model;

public final class AccountContext {
    private static AccountContext instance = null;
    private User viewedUser;

    private AccountContext() {
    }

    public static AccountContext getInstance() {
        if (instance == null) {
            instance = new AccountContext();
        }
        return instance;
    }

    public void setUserToView(User user) {
        this.viewedUser = user;
    }

    public User getUserToView() {
        return this.viewedUser;
    }

    public void clearUserToView() {
        this.viewedUser = null;
    }

    public boolean hasUserToView() {
        return this.viewedUser != null;
    }
}
