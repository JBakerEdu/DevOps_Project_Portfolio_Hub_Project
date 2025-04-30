package edu.westga.comp4420.project_portfolio.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectContextTest {

    @BeforeEach
    public void resetSingleton() {
        ProjectContext.getInstance().clear();
    }

    @Test
    public void testGetInstanceReturnsSameInstance() {
        ProjectContext instance1 = ProjectContext.getInstance();
        ProjectContext instance2 = ProjectContext.getInstance();

        assertSame(instance1, instance2, "Singleton instances should be the same");
    }

    @Test
    public void testSetAndGetSelectedProject() {
        Project testProject = new Project("Test", "desc", "link");
        ProjectContext.getInstance().setSelectedProject(testProject);

        Project result = ProjectContext.getInstance().getSelectedProject();
        assertEquals(testProject, result);
    }

    @Test
    public void testHasSelectedProjectReturnsTrueWhenSet() {
        Project testProject = new Project("Test", "desc", "link");
        ProjectContext.getInstance().setSelectedProject(testProject);

        assertTrue(ProjectContext.getInstance().hasSelectedProject());
    }

    @Test
    public void testClearResetsSelectedProject() {
        Project testProject = new Project("Test", "desc", "link");
        ProjectContext.getInstance().setSelectedProject(testProject);
        ProjectContext.getInstance().clear();

        assertNull(ProjectContext.getInstance().getSelectedProject());
        assertFalse(ProjectContext.getInstance().hasSelectedProject());
    }

    @Test
    public void testHasSelectedProjectReturnsFalseByDefault() {
        assertFalse(ProjectContext.getInstance().hasSelectedProject());
    }
}
