package edu.westga.comp4420.project_portfolio.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AccountManagerTest {

    @BeforeEach
    public void resetAccounts() throws Exception {
        Field accountsField = AccountManager.class.getDeclaredField("accounts");
        accountsField.setAccessible(true);
        List<User> accounts = (List<User>) accountsField.get(null);
        accounts.clear();
    }

    @Test
    public void testCreateAccountSuccess() {
        boolean result = AccountManager.createAccount("user1", "pass", "user1@example.com");
        assertTrue(result);
    }

    @Test
    public void testCreateAccountFailsDuplicateUsername() {
        AccountManager.createAccount("user1", "pass", "user1@example.com");
        boolean result = AccountManager.createAccount("user1", "pass", "another@example.com");
        assertFalse(result);
    }

    @Test
    public void testCreateAccountFailsDuplicateEmail() {
        AccountManager.createAccount("user1", "pass", "user1@example.com");
        boolean result = AccountManager.createAccount("another", "pass", "user1@example.com");
        assertFalse(result);
    }

    @Test
    public void testValidateLoginSuccess() {
        AccountManager.createAccount("user1", "pass", "user1@example.com");
        User user = AccountManager.validateLogin("user1", "pass");
        assertNotNull(user);
    }

    @Test
    public void testValidateLoginFailsIncorrectPassword() {
        AccountManager.createAccount("user1", "pass", "user1@example.com");
        User user = AccountManager.validateLogin("user1", "wrong");
        assertNull(user);
    }

    @Test
    public void testValidateLoginFailsUnknownUser() {
        User user = AccountManager.validateLogin("ghost", "none");
        assertNull(user);
    }

    @Test
    public void testFindUserByUsernameOrEmailBothPaths() {
        AccountManager.createAccount("user1", "pass", "user1@example.com");

        assertNotNull(AccountManager.findUserByUsernameOrEmail("user1"));
        assertNotNull(AccountManager.findUserByUsernameOrEmail("user1@example.com"));
        assertNull(AccountManager.findUserByUsernameOrEmail("unknown"));
    }
	
	@Test
	public void testLogin_TrueTrue() {
		AccountManager.createAccount("user1", "pass123", "user1@example.com");
		User result = AccountManager.validateLogin("user1", "pass123"); // true && true
		assertNotNull(result);
	}

	@Test
	public void testLogin_TrueFalse() {
		AccountManager.createAccount("user1", "pass123", "user1@example.com");
		User result = AccountManager.validateLogin("user1", "wrongpass"); // true && false
		assertNull(result);
	}

	@Test
	public void testLogin_FalseTrue() {
		AccountManager.createAccount("user1", "pass123", "user1@example.com");
		// Must create another user with the password "pass123" but different username to test this
		AccountManager.createAccount("wronguser", "pass123", "other@example.com");
		User result = AccountManager.validateLogin("wronguser2", "pass123"); // false && true
		assertNull(result);
	}

	@Test
	public void testLogin_FalseFalse() {
		AccountManager.createAccount("user1", "pass123", "user1@example.com");
		User result = AccountManager.validateLogin("nouser", "wrongpass"); // false && false
		assertNull(result);
	}

    @Test
    public void testFindUserByUsernameHitAndMiss() {
        AccountManager.createAccount("user1", "pass", "user1@example.com");

        assertNotNull(AccountManager.findUserByUsername("user1")); // hit
        assertNull(AccountManager.findUserByUsername("notfound")); // miss
    }

    @Test
    public void testFindUserByEmailHitAndMiss() {
        AccountManager.createAccount("user1", "pass", "user1@example.com");

        assertNotNull(AccountManager.findUserByEmail("user1@example.com")); // hit
        assertNull(AccountManager.findUserByEmail("missing@example.com"));  // miss
    }

    @Test
    public void testGetAllAccounts() {
        AccountManager.createAccount("user1", "pass", "user1@example.com");
        AccountManager.createAccount("user2", "pass", "user2@example.com");

        List<User> accounts = AccountManager.getAllAccounts();
        assertEquals(2, accounts.size());
    }

    @Test
    public void testConstructor() {
        new AccountManager(); // just to hit constructor
    }
}
