package edu.westga.comp4420.project_portfolio.model;

public final class ProjectContext {
    private static ProjectContext instance = null;
    private Project selectedProject;

    private ProjectContext() {
    }

    public static ProjectContext getInstance() {
        if (instance == null) {
            instance = new ProjectContext();
        }
        return instance;
    }

    public void setSelectedProject(Project project) {
        this.selectedProject = project;
    }

    public Project getSelectedProject() {
        return this.selectedProject;
    }

    public void clear() {
        this.selectedProject = null;
    }

    public boolean hasSelectedProject() {
        return this.selectedProject != null;
    }
}
