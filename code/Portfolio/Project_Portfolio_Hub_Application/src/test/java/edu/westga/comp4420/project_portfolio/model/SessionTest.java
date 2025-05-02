package edu.westga.comp4420.project_portfolio.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SessionTest {

    @BeforeEach
    public void resetSession() {
        Session.getInstance().logout();
    }

    @Test
    public void testSingletonReturnsSameInstance() {
        Session session1 = Session.getInstance();
        Session session2 = Session.getInstance();

        assertSame(session1, session2);
    }

    @Test
    public void testLoginSetsCurrentUser() {
        User user = new User("testUser", "pass", "test@example.com");
        Session.getInstance().login(user);

        assertEquals(user, Session.getInstance().getCurrentUser());
        assertTrue(Session.getInstance().isLoggedIn());
    }

    @Test
    public void testLogoutClearsCurrentUser() {
        User user = new User("testUser", "pass", "test@example.com");
        Session.getInstance().login(user);
        Session.getInstance().logout();

        assertNull(Session.getInstance().getCurrentUser());
        assertFalse(Session.getInstance().isLoggedIn());
    }

    @Test
    public void testIsLoggedInReturnsFalseByDefault() {
        assertFalse(Session.getInstance().isLoggedIn());
    }
}
