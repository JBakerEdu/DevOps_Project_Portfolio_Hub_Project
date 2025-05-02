package edu.westga.comp4420.project_portfolio.util;

import edu.westga.comp4420.project_portfolio.model.AccountManager;
import edu.westga.comp4420.project_portfolio.model.User;
import edu.westga.comp4420.project_portfolio.model.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PopulateSampleDataTest {

    @BeforeEach
    public void resetAccounts() throws Exception {
        Field accountsField = AccountManager.class.getDeclaredField("accounts");
        accountsField.setAccessible(true);
        List<User> accounts = (List<User>) accountsField.get(null);
        accounts.clear();
    }

    @Test
    public void testPopulateSampleDataCreatesExpectedUsersAndProjects() {
        PopulateSampleData.populateSampleData();

        List<User> users = AccountManager.getAllAccounts();
        assertEquals(5, users.size(), "Should create 5 users");

        for (int i = 1; i <= 5; i++) {
            String username = "user" + i;
            User user = AccountManager.findUserByUsername(username);
            assertNotNull(user, "User should exist: " + username);
            assertEquals("Hello, I am " + username + " and I build cool stuff!", user.getDescription());

            List<Project> projects = user.getProjectManager().getProjects();
            assertEquals(2, projects.size(), "Each user should have 2 projects");

            assertTrue(projects.get(0).getName().contains("Alpha"));
            assertTrue(projects.get(1).getName().contains("Beta"));
        }
    }

    @Test
    public void testDuplicateUserIsSkipped() {
        AccountManager.createAccount("user1", "existing", "user1@example.com");
        PopulateSampleData.populateSampleData();
        assertEquals(5, AccountManager.getAllAccounts().size());
    }
}
