package edu.westga.comp4420.project_portfolio.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        this.user = new User("testuser", "testpass", "test@example.com");
    }

    @Test
    public void testConstructorInitializesFieldsCorrectly() {
        assertEquals("testuser", user.getUsername());
        assertEquals("testpass", user.getPassword());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("", user.getDescription());
        assertNotNull(user.getProjectManager());
    }

    @Test
    public void testSetDescriptionUpdatesDescription() {
        user.setDescription("New user bio");
        assertEquals("New user bio", user.getDescription());
    }

    @Test
    public void testGetProjectManagerReturnsSameInstance() {
        ProjectManager manager1 = user.getProjectManager();
        ProjectManager manager2 = user.getProjectManager();
        assertSame(manager1, manager2);
    }
}
