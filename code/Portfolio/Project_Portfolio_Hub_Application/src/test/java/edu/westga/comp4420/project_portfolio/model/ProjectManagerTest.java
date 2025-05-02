package edu.westga.comp4420.project_portfolio.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectManagerTest {

    private ProjectManager manager;

    @BeforeEach
    public void setUp() {
        this.manager = new ProjectManager();
    }

    @Test
    public void testAddProjectAddsCorrectly() {
        manager.addProject("Project1", "Desc", "Link", null);

        List<Project> projects = manager.getProjects();
        assertEquals(1, projects.size());
        assertEquals("Project1", projects.get(0).getName());
    }

    @Test
    public void testAddProjectWithRootDirectory() {
        File root = new File("some/path");
        manager.addProject("Project2", "Desc", "Link", root);

        Project result = manager.getProjects().get(0);
        assertEquals(root, result.getRootDirectory());
    }

    @Test
    public void testAddProjectWithNullNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            manager.addProject(null, "Desc", "Link", null);
        });
    }

    @Test
    public void testAddProjectWithBlankNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            manager.addProject("   ", "Desc", "Link", null);
        });
    }

    @Test
    public void testRemoveProjectActuallyRemoves() {
        manager.addProject("ToRemove", "Desc", "Link", null);
        Project project = manager.getProjects().get(0);

        manager.removeProject(project);

        assertTrue(manager.getProjects().isEmpty());
    }

    @Test
    public void testGetProjectsReturnsCorrectList() {
        manager.addProject("A", "Desc", "Link", null);
        manager.addProject("B", "Desc", "Link", null);

        List<Project> projects = manager.getProjects();

        assertEquals(2, projects.size());
        assertEquals("A", projects.get(0).getName());
        assertEquals("B", projects.get(1).getName());
    }

    @Test
    public void testGetProjectByNameFindsProject() {
        manager.addProject("UniqueProject", "Desc", "Link", null);

        Project found = manager.getProjectByName("UniqueProject");
        assertNotNull(found);
        assertEquals("UniqueProject", found.getName());
    }

    @Test
    public void testGetProjectByNameIsCaseInsensitive() {
        manager.addProject("CaseTest", "Desc", "Link", null);

        Project found = manager.getProjectByName("casetest");
        assertNotNull(found);
        assertEquals("CaseTest", found.getName());
    }

    @Test
    public void testGetProjectByNameReturnsNullIfNotFound() {
        manager.addProject("Exists", "Desc", "Link", null);

        Project found = manager.getProjectByName("DoesNotExist");
        assertNull(found);
    }

    @Test
    public void testGetProjectByNameReturnsNullIfNullPassed() {
        assertNull(manager.getProjectByName(null));
    }

    @Test
    public void testGetProjectByNameReturnsNullIfBlankPassed() {
        assertNull(manager.getProjectByName("   "));
    }
}
