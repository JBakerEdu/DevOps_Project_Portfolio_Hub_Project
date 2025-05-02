package edu.westga.comp4420.project_portfolio.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountContextTest {

    @BeforeEach
    public void resetContext() {
        AccountContext.getInstance().clearUserToView();
    }

    @Test
    public void testSingletonReturnsSameInstance() {
        AccountContext context1 = AccountContext.getInstance();
        AccountContext context2 = AccountContext.getInstance();

        assertSame(context1, context2);
    }

    @Test
    public void testSetAndGetUserToView() {
        User user = new User("viewedUser", "pass", "viewed@example.com");
        AccountContext.getInstance().setUserToView(user);

        assertEquals(user, AccountContext.getInstance().getUserToView());
    }

    @Test
    public void testHasUserToViewReturnsTrueWhenSet() {
        User user = new User("anotherUser", "pass", "another@example.com");
        AccountContext.getInstance().setUserToView(user);

        assertTrue(AccountContext.getInstance().hasUserToView());
    }

    @Test
    public void testClearUserToViewResetsContext() {
        User user = new User("temp", "pass", "temp@example.com");
        AccountContext.getInstance().setUserToView(user);
        AccountContext.getInstance().clearUserToView();

        assertNull(AccountContext.getInstance().getUserToView());
        assertFalse(AccountContext.getInstance().hasUserToView());
    }

    @Test
    public void testHasUserToViewReturnsFalseByDefault() {
        assertFalse(AccountContext.getInstance().hasUserToView());
    }
}
