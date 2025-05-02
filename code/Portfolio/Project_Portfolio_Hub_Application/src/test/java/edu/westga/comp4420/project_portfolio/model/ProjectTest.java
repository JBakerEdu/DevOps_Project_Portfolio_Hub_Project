package edu.westga.comp4420.project_portfolio.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectTest {

    private Project project;

    @BeforeEach
    public void setUp() {
        this.project = new Project("Initial Name", "Initial Description", "http://example.com");
    }

    @Test
    public void testConstructorInitializesFieldsCorrectly() {
        assertEquals("Initial Name", project.getName());
        assertEquals("Initial Description", project.getDescription());
        assertEquals("http://example.com", project.getHyperlink());
        assertNotNull(project.getLastEdited());
        assertNull(project.getRootDirectory());
    }

    @Test
    public void testSetNameUpdatesNameAndLastEdited() {
        LocalDateTime before = project.getLastEdited();
        sleepBriefly();
        project.setName("Updated Name");

        assertEquals("Updated Name", project.getName());
        assertTrue(project.getLastEdited().isAfter(before));
    }

    @Test
    public void testSetDescriptionUpdatesDescriptionAndLastEdited() {
        LocalDateTime before = project.getLastEdited();
        sleepBriefly();
        project.setDescription("Updated Description");

        assertEquals("Updated Description", project.getDescription());
        assertTrue(project.getLastEdited().isAfter(before));
    }

    @Test
    public void testSetHyperlinkUpdatesHyperlinkAndLastEdited() {
        LocalDateTime before = project.getLastEdited();
        sleepBriefly();
        project.setHyperlink("http://newlink.com");

        assertEquals("http://newlink.com", project.getHyperlink());
        assertTrue(project.getLastEdited().isAfter(before));
    }

    @Test
    public void testSetRootDirectoryUpdatesRootAndLastEdited() {
        File dummyFile = new File("dummy/path");
        LocalDateTime before = project.getLastEdited();
        sleepBriefly();
        project.setRootDirectory(dummyFile);

        assertEquals(dummyFile, project.getRootDirectory());
        assertTrue(project.getLastEdited().isAfter(before));
    }

    @Test
    public void testGetFormattedLastEditedReturnsCorrectFormat() {
        String formatted = project.getFormattedLastEdited();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        assertEquals(formatter.format(project.getLastEdited()), formatted);
    }

    private void sleepBriefly() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
