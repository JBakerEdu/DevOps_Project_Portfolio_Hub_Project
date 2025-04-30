package edu.westga.comp4420.project_portfolio.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountManagerTest {

    @BeforeEach
    public void resetAccounts() {
        var accountsField = AccountManager.class.getDeclaredFields()[0];
        accountsField.setAccessible(true);
        try {
            ((java.util.List<?>) accountsField.get(null)).clear();
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to reset static accounts list", e);
        }
    }

    @Test
    public void testCreateAccountSuccess() {
        boolean result = AccountManager.createAccount("user1", "pass1", "user1@example.com");
        assertTrue(result);

        User user = AccountManager.findUserByUsername("user1");
        assertNotNull(user);
        assertEquals("user1@example.com", user.getEmail());
    }

    @Test
    public void testCreateAccountFailsWithDuplicateUsername() {
        AccountManager.createAccount("user2", "pass", "user2@example.com");

        boolean result = AccountManager.createAccount("user2", "newpass", "different@example.com");
        assertFalse(result);
    }

    @Test
    public void testCreateAccountFailsWithDuplicateEmail() {
        AccountManager.createAccount("user3", "pass", "duplicate@example.com");

        boolean result = AccountManager.createAccount("anotherUser", "pass", "duplicate@example.com");
        assertFalse(result);
    }

    @Test
    public void testValidateLoginSuccess() {
        AccountManager.createAccount("user4", "secure", "user4@example.com");

        User user = AccountManager.validateLogin("user4", "secure");
        assertNotNull(user);
        assertEquals("user4", user.getUsername());
    }

    @Test
    public void testValidateLoginFailsWithWrongPassword() {
        AccountManager.createAccount("user5", "rightpass", "user5@example.com");

        User user = AccountManager.validateLogin("user5", "wrongpass");
        assertNull(user);
    }

    @Test
    public void testValidateLoginFailsForUnknownUser() {
        User user = AccountManager.validateLogin("ghost", "nopass");
        assertNull(user);
    }

    @Test
    public void testFindUserByUsernameOrEmail() {
        AccountManager.createAccount("user6", "pass", "user6@example.com");

        assertNotNull(AccountManager.findUserByUsernameOrEmail("user6"));
        assertNotNull(AccountManager.findUserByUsernameOrEmail("user6@example.com"));
        assertNull(AccountManager.findUserByUsernameOrEmail("notfound"));
    }

    @Test
    public void testFindUserByUsername() {
        AccountManager.createAccount("user7", "pass", "user7@example.com");

        User user = AccountManager.findUserByUsername("user7");
        assertNotNull(user);
        assertEquals("user7", user.getUsername());
    }

    @Test
    public void testFindUserByEmail() {
        AccountManager.createAccount("user8", "pass", "user8@example.com");

        User user = AccountManager.findUserByEmail("user8@example.com");
        assertNotNull(user);
        assertEquals("user8@example.com", user.getEmail());
    }
}
